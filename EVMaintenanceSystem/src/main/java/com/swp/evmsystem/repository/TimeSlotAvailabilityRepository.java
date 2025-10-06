package com.swp.evmsystem.repository;

import com.swp.evmsystem.entity.ServiceCenterEntity;
import com.swp.evmsystem.entity.TimeSlotAvailability;
import com.swp.evmsystem.enums.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TimeSlotAvailabilityRepository extends JpaRepository<TimeSlotAvailability, Integer> {
    List<TimeSlotAvailability> findAllByDate(LocalDate date);

    TimeSlotAvailability save(TimeSlotAvailability timeSlotAvailability);

    TimeSlotAvailability findTimeSlotAvailabilityByDateAndTimeSlotAndServiceCenter(LocalDate date, TimeSlot timeSlot, ServiceCenterEntity serviceCenter);
}
