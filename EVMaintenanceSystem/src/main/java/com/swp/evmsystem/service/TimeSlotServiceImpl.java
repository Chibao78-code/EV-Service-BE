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
                .orElseThrow(() -> new EntityNotFoundException("ServiceCenter not found with id: " + centerId)); // Ném ngoại lệ nếu không tìm thấy

        // 2. Lấy số bookings cho từng slot
        List<BookingRepository.BookingCountProjection> bookingCounts =
                bookingRepository.findByCenterAndBookingDate(center, date); // Lấy số lượng booking theo từng khung giờ


        // Convert to Map để lookup nhanh
        Map<LocalTime, Integer> bookingCountMap = bookingCounts.stream()
                .collect(Collectors.toMap( // Chuyển đổi danh sách projection thành Map
                        BookingRepository.BookingCountProjection::getTime, // Khóa: thời gian
                        BookingRepository.BookingCountProjection::getCount // Giá trị: số lượng booking
                ));

        // 3. Tạo TimeSlot objects
        LocalTime start = center.getStartTime(); // thời gian bắt đầu làm việc của trung tâm
        LocalTime end = center.getEndTime(); // thời gian kết thúc làm việc của trung tâm
        int slotMinutes = 30; // độ dài mỗi slot (1h)

        List<TimeSlotDTO> slots = Stream
                .iterate(start, time -> time.isBefore(end), time -> time.plusMinutes(slotMinutes))
                .map(time -> { // Tạo TimeSlotDTO cho mỗi khung giờ
                    int numOfBookings = bookingCountMap.getOrDefault(time, 0); // Lấy số lượng booking đã có cho khung giờ này

                    return TimeSlotDTO.builder()
                            .date(date)
                            .time(time)
                            .centerId(centerId)
                            .numOfBookings(numOfBookings)
                            .maxCapacity(center.getMaxCapacity())
                            .available(numOfBookings < center.getMaxCapacity())
                            .build(); // Trả về đối tượng TimeSlotDTO
                })
                .collect(Collectors.toList()); // Thu thập tất cả các TimeSlotDTO vào danh sách


        // 4. Build response
        return TimeSlotResponseDTO.builder()
                .date(date)
                .center(serviceCenterMapper.toDTO(center))
                .slots(slots)
                .build(); // Trả về đối tượng TimeSlotResponseDTO
    }
}
