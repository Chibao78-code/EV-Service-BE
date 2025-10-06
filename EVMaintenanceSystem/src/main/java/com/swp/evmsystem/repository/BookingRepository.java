package com.swp.evmsystem.repository;

import com.swp.evmsystem.entity.BookingEntity;
import com.swp.evmsystem.enums.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {
    BookingEntity save(BookingEntity bookingEntity);
    int countAllByBookingDateAndTimeSlot(LocalDate bookingDate, TimeSlot timeSlot);
}
