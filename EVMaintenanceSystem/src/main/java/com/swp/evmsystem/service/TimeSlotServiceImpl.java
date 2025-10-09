package com.swp.evmsystem.service;

import com.swp.evmsystem.dto.response.ServiceCenterDTO;
import com.swp.evmsystem.dto.response.TimeSlotDTO;
import com.swp.evmsystem.dto.response.TimeSlotResponseDTO;
import com.swp.evmsystem.entity.ServiceCenterEntity;
import com.swp.evmsystem.mapper.ServiceCenterMapper;
import com.swp.evmsystem.repository.BookingRepository;
import com.swp.evmsystem.repository.ServiceCenterRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TimeSlotServiceImpl implements TimeSlotService {
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private ServiceCenterRepository centerRepository;
    @Autowired
    private ServiceCenterMapper serviceCenterMapper;

    public TimeSlotResponseDTO getTimeSlots(int centerId, LocalDate date) {

        // 1. Lấy thông tin center
        ServiceCenterEntity center = centerRepository.findById(centerId)
                .orElseThrow(() -> new EntityNotFoundException("ServiceCenter not found with id: " + centerId));

        // 2. Lấy số bookings cho từng slot
        List<BookingRepository.BookingCountProjection> bookingCounts =
                bookingRepository.findByCenterAndBookingDate(center, date);


        // Convert to Map để lookup nhanh
        Map<LocalTime, Integer> bookingCountMap = bookingCounts.stream()
                .collect(Collectors.toMap(
                        BookingRepository.BookingCountProjection::getTime,
                        BookingRepository.BookingCountProjection::getCount
                ));

        // 3. Tạo TimeSlot objects
        LocalTime start = center.getStartTime();
        LocalTime end = center.getEndTime();
        int slotMinutes = 60; // độ dài mỗi slot (1h)

        List<TimeSlotDTO> slots = Stream
                .iterate(start, time -> time.isBefore(end), time -> time.plusMinutes(slotMinutes))
                .map(time -> {
                    int numOfBookings = bookingCountMap.getOrDefault(time, 0);

                    return TimeSlotDTO.builder()
                            .date(date)
                            .time(time)
                            .centerId(centerId)
                            .numOfBookings(numOfBookings)
                            .maxCapacity(center.getMaxCapacity())
                            .available(numOfBookings < center.getMaxCapacity())
                            .build();
                })
                .collect(Collectors.toList());


        // 4. Build response
        return TimeSlotResponseDTO.builder()
                .date(date)
                .center(serviceCenterMapper.toDTO(center))
                .slots(slots)
                .build();
    }
}
