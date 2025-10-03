package com.swp.evmsystem.controller;

import com.swp.evmsystem.dto.request.BookingRequest;
import com.swp.evmsystem.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class BookingController {
    @Autowired
    BookingService bookingService;



    @PostMapping("/vehicles/{vehicleId}/bookings")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> createBooking(@Valid @RequestBody BookingRequest request) {

        boolean isCreated = bookingService.createBooking(request);
        if (isCreated) {
            return ResponseEntity.ok("Booking created successfully");
        } else {
            return ResponseEntity.status(500).body("Failed to create booking");
        }
    }

    @GetMapping("/bookings/{bookingId}")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<?> getBookingById(@PathVariable Integer bookingId) {
        var booking = bookingService.getBookingById(bookingId);
        if (booking != null) {
            return ResponseEntity.ok(booking);
        } else {
            return ResponseEntity.status(404).body("Booking not found");
        }
    }

    @GetMapping("/bookings/date/{date}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> getBookingsByDate(@PathVariable String date) {

    }
}
