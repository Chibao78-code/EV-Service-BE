package com.swp.evmsystem.repository;

import com.swp.evmsystem.entity.BookingEntity;
import com.swp.evmsystem.entity.ServiceCenterEntity;
import com.swp.evmsystem.enums.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<BookingEntity, Integer> {
    BookingEntity save(BookingEntity bookingEntity);

    int countAllByBookingDateAndTimeSlot(LocalDate bookingDate, TimeSlot timeSlot);

    int countBookingEntityByCenterIdAndBookingDate(Integer centerId, LocalDate bookingDate);

    interface BookingCountProjection {
        LocalTime getTime();

        Integer getCount();
    }

    @Query("""
                SELECT b.timeSlot AS timeSlot, COUNT(b.id) AS count
                FROM BookingEntity b
                WHERE b.center = :center AND b.bookingDate = :date
                GROUP BY b.timeSlot
            """)
    List<BookingCountProjection> findByCenterAndBookingDate(
            @Param("center") ServiceCenterEntity center,
            @Param("date") LocalDate date
    );
}
