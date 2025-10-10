package com.swp.evmsystem.service;

import com.swp.evmsystem.dto.request.BookingRequestDTO;
import com.swp.evmsystem.dto.response.BookingResponseDTO;
import com.swp.evmsystem.entity.BookingEntity;
import com.swp.evmsystem.entity.CustomerEntity;
import com.swp.evmsystem.enums.TimeSlot;
import com.swp.evmsystem.repository.BookingRepository;
import com.swp.evmsystem.repository.ElectricVehicleRepository;
import com.swp.evmsystem.repository.ServiceCenterRepository;
import com.swp.evmsystem.security.UserEntityDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;


@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private ElectricVehicleRepository vehicleRepository;
    @Autowired
    private ServiceCenterRepository centerRepository;


    @Override
    public BookingResponseDTO createBooking(BookingRequestDTO request) {
        Integer eVId = request.getEVId();
        Integer centerId = request.getCenterId();
        LocalDate date = request.getBookingDate();
        TimeSlot timeSlot = request.getTimeSlot();

        BookingEntity be = new BookingEntity();
        be.setVehicle(vehicleRepository.getOne(eVId));
        be.setCenter(centerRepository.getOne(centerId));
        be.setBookingDate(date);
        be.setTimeSlot(timeSlot);
        BookingEntity bookingEntity = bookingRepository.save(be);
        
    }

    @Override
    public boolean isEVBelongToCustomer(BookingRequestDTO request, UserEntityDetails user) {
        Integer eVId = request.getEVId();
        CustomerEntity owner = (CustomerEntity) user.getUser();
        return vehicleRepository.existsElectricVehicleEntityByIdAndOwner(eVId, owner);
    } // Kiểm tra xe có thuộc về người đặt lịch
}
