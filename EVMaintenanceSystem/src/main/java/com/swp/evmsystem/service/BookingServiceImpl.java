package com.swp.evmsystem.service;

import com.swp.evmsystem.dto.request.BookingRequestDTO;
import com.swp.evmsystem.dto.response.BookingResponseDTO;
import com.swp.evmsystem.entity.BookingEntity;
import com.swp.evmsystem.entity.CustomerEntity;
import com.swp.evmsystem.entity.ElectricVehicleEntity;
import com.swp.evmsystem.entity.ServiceCenterEntity;
import com.swp.evmsystem.enums.BookingStatus;
import com.swp.evmsystem.enums.EvMaintenanceStatus;
import com.swp.evmsystem.repository.BookingRepository;
import com.swp.evmsystem.repository.ElectricVehicleRepository;
import com.swp.evmsystem.repository.ServiceCenterRepository;
import com.swp.evmsystem.security.UserEntityDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


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
        ElectricVehicleEntity electricVehicle = vehicleRepository.findById(request.getEVId()).get();
        ServiceCenterEntity serviceCenter = centerRepository.findById(request.getCenterId()).get();
        LocalDate bookingDate = request.getBookingDate();
        LocalTime bookingTime = request.getBookingTime();

        BookingEntity be = BookingEntity.builder()
                .vehicle(electricVehicle)
                .center(serviceCenter)
                .bookingDate(bookingDate)
                .bookingTime(bookingTime)
                .status(BookingStatus.PENDING)
                .build();
        bookingRepository.save(be);

        electricVehicle.setMaintenanceStatus(EvMaintenanceStatus.BOOKED);
        vehicleRepository.save(electricVehicle);

        return BookingResponseDTO.builder()
                .center(serviceCenter.getCenterName())
                .address(serviceCenter.getCenterAddress().toString())
                .date(bookingDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .time(bookingTime.format(DateTimeFormatter.ofPattern("HH:mm")))
                .eVModel(electricVehicle.getModel().getModelName())
                .build();

    }

    @Override
    public boolean isEVBelongToCustomer(BookingRequestDTO request, UserEntityDetails user) {
        Integer eVId = request.getEVId();
        CustomerEntity owner = (CustomerEntity) user.getUserEntity();

        return vehicleRepository.existsElectricVehicleEntityByIdAndOwner_Id(eVId, owner.getId());
    } // Kiểm tra xe có thuộc về người đặt lịch

    @Override
    public boolean isTimeBookingAvailable(BookingRequestDTO request) {
        ServiceCenterEntity ct = centerRepository.findById(request.getCenterId()).get();

        return bookingRepository.countBookingsAtTime(ct, request.getBookingDate(), request.getBookingTime()) < ct.getMaxCapacity();
    }


}
