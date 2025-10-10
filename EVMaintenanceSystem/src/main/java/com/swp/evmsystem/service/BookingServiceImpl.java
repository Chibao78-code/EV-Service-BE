package com.swp.evmsystem.service;

import com.swp.evmsystem.dto.request.BookingRequestDTO;
import com.swp.evmsystem.entity.BookingEntity;
import com.swp.evmsystem.enums.BookingStatus;
import com.swp.evmsystem.enums.TimeSlot;
import com.swp.evmsystem.repository.BookingRepository;
import com.swp.evmsystem.repository.ElectricVehicleRepository;
import com.swp.evmsystem.repository.ServiceCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private ElectricVehicleRepository vehicleService;
    @Autowired
    private ServiceCenterRepository serviceCenterRepository;

    @Override
    public boolean createBooking(Integer id , BookingRequestDTO request) {
        BookingEntity bookingEntity = new BookingEntity();
        bookingEntity.setBookingDate(request.getBookingDate());
        //bookingEntity.setCenter(serviceCenterRepository.findById(request.getCenterId()).orElse(null));
        bookingEntity.setVehicle(vehicleService.findById(id).orElse(null));
        bookingEntity.setStatus(BookingStatus.PENDING);
        bookingEntity.setTimeSlot(request.getTimeSlot());
        return bookingRepository.save(bookingEntity) != null;
    }

    @Override
    public BookingEntity getBookingById(Integer bookingId) {
        return bookingRepository.findById(bookingId).orElse(null);
    }

    @Override
    public int getBookingsByDateAndTimeSlot(LocalDate bookingDate, TimeSlot timeSlot) {
        return bookingRepository.countAllByBookingDateAndTimeSlot( bookingDate,  timeSlot);
    }

    @Override
    public int countBookingsByCenterAndDate(int centerId, LocalDate bookingDate) {
        return bookingRepository.countBookingEntityByCenterIdAndBookingDate(centerId, bookingDate);
    }
}
