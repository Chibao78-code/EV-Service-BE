package com.swp.evmsystem.controller;

import com.swp.evmsystem.dto.request.BookingRequest;
import com.swp.evmsystem.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {
    @Autowired
    BookingService bookingService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> createBooking(@Valid @RequestBody BookingRequest request) {
        boolean isCreated = bookingService.createBooking(request);
        if (isCreated) {
            return ResponseEntity.ok("Booking created successfully");
        } else {
            return ResponseEntity.status(500).body("Failed to create booking");
        }
    }
}
