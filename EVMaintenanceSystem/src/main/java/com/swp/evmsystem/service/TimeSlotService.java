package com.swp.evmsystem.service;

import com.swp.evmsystem.dto.response.TimeSlotResponseDTO;
import com.swp.evmsystem.entity.ServiceCenterEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface TimeSlotService {
    TimeSlotResponseDTO getTimeSlots(int centerId, LocalDate date);
}
