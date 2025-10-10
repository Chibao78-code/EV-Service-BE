package com.swp.evmsystem.controller;

import com.swp.evmsystem.dto.request.BookingRequestDTO;
import com.swp.evmsystem.dto.response.BookingResponseDTO;
import com.swp.evmsystem.dto.response.OfferTypeDTO;
import com.swp.evmsystem.dto.response.TimeSlotResponseDTO;
import com.swp.evmsystem.security.UserEntityDetails;
import com.swp.evmsystem.service.BookingService;
import com.swp.evmsystem.service.OfferTypeService;
import com.swp.evmsystem.service.TimeSlotService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.nio.file.AccessDeniedException;
import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api")
public class BookingController {
    @Autowired
    private TimeSlotService timeSlotService;
    @Autowired
    private OfferTypeService offerTypeService;
    @Autowired
    private BookingService bookingService;

    @PostMapping("/bookings")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> addBooking(
            @AuthenticationPrincipal UserEntityDetails userDetails,
            @Valid @RequestBody BookingRequestDTO request) {

        try {
            if (!bookingService.isEVBelongToCustomer(request, userDetails)) {
                throw new AccessDeniedException("EV not belong to Customer");
            }

            if(!bookingService.isTimeBookingAvailable(request)) {
                throw new AccessDeniedException("TimeSlot not available");
            }

            BookingResponseDTO responseDTO = bookingService.createBooking(request);

            return ResponseEntity.ok(responseDTO);
        } catch (AccessDeniedException ex) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ex.getMessage());
        }
    }


    @GetMapping("/bookings/{centerId}/{date}")
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
