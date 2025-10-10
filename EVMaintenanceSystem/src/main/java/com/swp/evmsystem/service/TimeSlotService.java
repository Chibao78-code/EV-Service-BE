package com.swp.evmsystem.service;

import com.swp.evmsystem.dto.response.TimeSlotResponseDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;

@Service
public interface TimeSlotService {
    TimeSlotResponseDTO getTimeSlots(int centerId, LocalDate date);
}
