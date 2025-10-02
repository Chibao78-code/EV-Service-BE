package com.swp.evmsystem.repository;

import com.swp.evmsystem.entity.BookingEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {
    BookingEntity save(BookingEntity bookingEntity);
}
