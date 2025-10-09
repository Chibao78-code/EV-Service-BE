package com.swp.evmsystem.controller;

import com.swp.evmsystem.dto.response.OfferTypeDTO;
import com.swp.evmsystem.dto.response.TimeSlotResponseDTO;
import com.swp.evmsystem.security.UserEntityDetails;
import com.swp.evmsystem.service.OfferTypeService;
import com.swp.evmsystem.service.TimeSlotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingController {
    @Autowired
    private TimeSlotService timeSlotService;
    @Autowired
    private OfferTypeService offerTypeService;

    @PostMapping("bookings")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> addBooking(@AuthenticationPrincipal UserEntityDetails userDetails) {

    }

    @GetMapping("bookings/{centerId}/{date}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> getAvailableTimeSlotByDate(@Valid
                                                        @PathVariable("date") LocalDate date,
                                                        @PathVariable("centerId") Integer centerId) {
        TimeSlotResponseDTO timeSlotResponseDTO = timeSlotService.getTimeSlots(centerId, date);

        return ResponseEntity.ok(timeSlotResponseDTO);
    }

    @GetMapping("/offer-types")
    public ResponseEntity<List<OfferTypeDTO>> getAllOfferTypes(){
        return ResponseEntity.ok(offerTypeService.findAll());
    }
}
