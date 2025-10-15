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
    @PreAuthorize("hasRole('CUSTOMER')")// chỉ người dùng có role CUSTOMER mới được phép gọi
    public ResponseEntity<?> addBooking(@AuthenticationPrincipal UserEntityDetails userDetails) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("bookings/{centerId}/{date}")
    @PreAuthorize("hasRole('CUSTOMER')")
    public ResponseEntity<?> getAvailableTimeSlotByDate(@Valid
                                                        @PathVariable("date") LocalDate date,
                                                        @PathVariable("centerId") Integer centerId) {
        // Gọi service để lấy danh sách khung giờ trống của trung tâm trong ngày
        TimeSlotResponseDTO timeSlotResponseDTO = timeSlotService.getTimeSlots(centerId, date);
        return ResponseEntity.ok(timeSlotResponseDTO);
    }

    @GetMapping("/offer-types") //Lấy danh sách tất cả loại dịch vụ (bảo dưỡng, thay phụ tùng, sửa chữa, ...)
    public ResponseEntity<List<OfferTypeDTO>> getAllOfferTypes(){
        return ResponseEntity.ok(offerTypeService.findAll());
    }
}
