package com.swp.evmsystem.repository;

import com.swp.evmsystem.entity.BookingEntity;
import com.swp.evmsystem.entity.ServiceCenterEntity;
import org.springframework.data.domain.Sort;
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

    @Query("""
                SELECT COUNT(b)
                FROM BookingEntity b
                WHERE b.center = :center
                  AND b.bookingDate = :date
                  AND CAST(b.bookingTime AS time) = :time
            """)
    long countBookingsAtTime(
            @Param("center") ServiceCenterEntity center,
            @Param("date") LocalDate date,
            @Param("time") LocalTime time
    );

    List<BookingEntity> findByCenter_IdAndBookingDate(Integer centerId, LocalDate bookingDate);


    interface BookingCountProjection {
        LocalTime getTime();

        Integer getCount();
    }

    @Query("""
                SELECT b.bookingTime AS time, COUNT(b.bookingId) AS count
                FROM BookingEntity b
                WHERE b.center = :center AND b.bookingDate = :date
                GROUP BY b.bookingTime
            """)
    List<BookingCountProjection> findByCenterAndBookingDate(
            @Param("center") ServiceCenterEntity center,
            @Param("date") LocalDate date
    );


}
