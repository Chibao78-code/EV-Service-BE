package com.swp.evmsystem.controller;

import com.swp.evmsystem.dto.request.BookingRequest;
import com.swp.evmsystem.dto.response.AvaibleTimeSlotDto;
import com.swp.evmsystem.entity.TimeSlotAvailability;
import com.swp.evmsystem.service.BookingService;
import com.swp.evmsystem.service.ServiceCenterService;
import com.swp.evmsystem.service.TimeSlotAvailabilityService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @Autowired
    private ServiceCenterService serviceCenterService;
    @Autowired
    private TimeSlotAvailabilityService timeSlotAvailabilityService;

    @PostMapping("/vehicles/{vehicleId}/bookings")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> createBooking(@Valid @RequestBody BookingRequest request,
                                           @PathVariable("vehicleId") int vehicleId,
                                           @AuthenticationPrincipal UserDetails userDetails) {


        if (userDetails == null) {
            return ResponseEntity.status(401).body("User not authenticated");
        }

        boolean isTimeAvailable = timeSlotAvailabilityService.isTimeAvailable(request);


        if (isTimeAvailable) {
            boolean isCreated = bookingService.createBooking(vehicleId, request);
            if (isCreated) {
                return ResponseEntity.ok("Booking created successfully");
            } else {
                return ResponseEntity.status(401).body("Booking creation failed");
            }
        } else {
            return ResponseEntity.status(500).body("Booking creation failed as time being full");
        }
    }

    @GetMapping("bookings/{date}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> getAvailableTimeSlotByDate(@Valid
                                                        @PathVariable("date") LocalDate date) {
        List<TimeSlotAvailability> timeSlotStatusList = timeSlotAvailabilityService.getTimeSlotByDate(date);
        List<AvaibleTimeSlotDto> avaibleTimeSlotDtoList = new ArrayList<>();

        for (TimeSlotAvailability timeSlotAvailability : timeSlotStatusList) {
            avaibleTimeSlotDtoList.add(new AvaibleTimeSlotDto(timeSlotAvailability));
        }

        return ResponseEntity.ok(avaibleTimeSlotDtoList);
    }
}
