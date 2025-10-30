package com.swp.evmsystem.service;

import com.swp.evmsystem.dto.request.BookingRequestDTO;
import com.swp.evmsystem.dto.response.BookingResponseDTO;
import com.swp.evmsystem.entity.*;
import com.swp.evmsystem.enums.BookingStatus;
import com.swp.evmsystem.enums.EvMaintenanceStatus;
import com.swp.evmsystem.exception.BusinessException;
import com.swp.evmsystem.repository.BookingRepository;
import com.swp.evmsystem.repository.ElectricVehicleRepository;
import com.swp.evmsystem.repository.ServiceCenterRepository;
import com.swp.evmsystem.security.UserEntityDetails;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
public class BookingServiceImpl implements BookingService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private ElectricVehicleRepository vehicleRepository;
    @Autowired
    private ServiceCenterRepository centerRepository;

    @Override
    public ElectricVehicleEntity findElectricVehicleById(Integer evId) {
        if (evId == null) {
            throw new IllegalArgumentException("EVId must not be null");
        }

        return vehicleRepository.findById(evId)
                .orElseThrow(() -> new EntityNotFoundException("Electric vehicle not found"));
    } // Tìm xe theo Id, nếu không có thì quăng lỗi

    @Override
    public void validateOwnership(ElectricVehicleEntity ev, UserEntityDetails userDetails) {
        if (userDetails == null || userDetails.getUserEntity() == null) {
            throw new AccessDeniedException("Unauthenticated user");
        }

        UserEntity entity = userDetails.getUserEntity();

        if (!(entity instanceof CustomerEntity customer)) {
            throw new AccessDeniedException("Current user is not a customer");
        }

        if (ev.getOwner() == null || !Objects.equals(ev.getOwner().getId(), customer.getId())) {
            throw new AccessDeniedException("Vehicle does not belong to current user");
        }
    } // Method dùng để kiểm tra ownership giữa xe và người.

    public void validateTimeBookingAvailable(BookingRequestDTO request) {
        if (request == null) {
            throw new IllegalArgumentException("Booking request must not be null");
        }

        if (request.getCenterId() == null || request.getBookingDate() == null || request.getBookingTime() == null) {
            throw new BusinessException("CenterId, BookingDate and BookingTime must not be null");
        }

        ServiceCenterEntity center = centerRepository.findById(request.getCenterId())
                .orElseThrow(() -> new EntityNotFoundException(
                        "Service Center not found with id = " + request.getCenterId()
                ));

        long currentBookings = bookingRepository.countBookingsAtTime(
                center,
                request.getBookingDate(),
                request.getBookingTime()
        );

        if (currentBookings >= center.getMaxCapacity()) {
            throw new BusinessException("Time slot is already full at " +
                    center.getCenterName() + " (" + request.getBookingDate() + " " + request.getBookingTime() + ")");
        }
    }

    @Override
    public String bookingCodeGenerator(BookingRequestDTO request) {
        LocalDate date = request.getBookingDate();
        LocalTime time = request.getBookingTime();
        Integer evId = request.getEVId();
        Integer centerID = request.getCenterId();
        return "BK" + evId + (date.getYear() - 2000) + date.getMonthValue() + date.getDayOfMonth() + time.getHour() + "CT" + centerID;
    }

    @Override
    public List<BookingResponseDTO> getTodayBookings(UserEntityDetails userDetails) {
        if (userDetails == null || userDetails.getUserEntity() == null) {
            throw new AccessDeniedException("Unauthenticated user");
        }

        UserEntity entity = userDetails.getUserEntity();

        if (!(entity instanceof EmployeeEntity staff)) {
            throw new AccessDeniedException("Current user is not a service center staff");
        }

        Integer centerId = staff.getCenter().getId();
        LocalDate today = LocalDate.now();

        List<BookingEntity> bookings = bookingRepository.findByCenter_IdAndBookingDate(centerId, today);

        List<BookingResponseDTO> bookingDtos = bookings.stream()
                .map(booking -> BookingResponseDTO.builder()
                        .code(booking.getBookingCode())
                        .customerName(booking.getCustomerName())
                        .customerPhone(booking.getCustomerPhone())
                        .customerEmail(booking.getCustomerEmail())
                        .center(booking.getCenter().getCenterName())
                        .address(booking.getCenter().getCenterAddress().toString())
                        .date(booking.getBookingDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                        .time(booking.getBookingTime().format(DateTimeFormatter.ofPattern("HH:mm")))
                        .eVModel(booking.getVehicle().getModel().getModelName())
                        .build()
                ).collect(Collectors.toList());

        return bookingDtos;
    }

    @Override
    public BookingResponseDTO createBooking(BookingRequestDTO request, UserEntityDetails user) {
        ElectricVehicleEntity electricVehicle = findElectricVehicleById(request.getEVId());
        validateOwnership(electricVehicle, user);

        if (!(electricVehicle.getMaintenanceStatus() == EvMaintenanceStatus.AVAILABLE)) {
            throw new BusinessException("Maintenance is not available");
        } // Checking if the EV had been booked before.

        validateTimeBookingAvailable(request);
        // Check the Time is unable for booking.

        ServiceCenterEntity serviceCenter = centerRepository.findById(request.getCenterId()).get();
        LocalDate bookingDate = request.getBookingDate();
        LocalTime bookingTime = request.getBookingTime();
        String bookingCode = bookingCodeGenerator(request);

        BookingEntity bookingEntity = BookingEntity.builder()
                .vehicle(electricVehicle)
                .center(serviceCenter)
                .bookingDate(bookingDate)
                .bookingTime(bookingTime)
                .bookingCode(bookingCode)
                .status(BookingStatus.PENDING)
                .build();
        bookingRepository.save(bookingEntity);

        electricVehicle.setMaintenanceStatus(EvMaintenanceStatus.BOOKED); // Set status of EV to BOOKED
        vehicleRepository.save(electricVehicle);

        return BookingResponseDTO.builder()
                .code(bookingCode)
                .center(serviceCenter.getCenterName())
                .address(serviceCenter.getCenterAddress().toString())
                .date(bookingDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .time(bookingTime.format(DateTimeFormatter.ofPattern("HH:mm")))
                .eVModel(electricVehicle.getModel().getModelName())
                .build();
    }
}
