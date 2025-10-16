package com.swp.evmsystem.validation.validator;

import com.swp.evmsystem.dto.request.BookingRequestDTO;
import com.swp.evmsystem.validation.annotation.ValidBookingDateTime;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class BookingDateTimeValidator implements ConstraintValidator<ValidBookingDateTime, BookingRequestDTO> {
    @Override
    public boolean isValid(BookingRequestDTO dto, ConstraintValidatorContext context) {
        if (dto.getBookingDate() == null || dto.getBookingTime() == null)
            return true;

        context.disableDefaultConstraintViolation();
        if(dto.getBookingDate().isBefore(LocalDate.now())) {
            context.buildConstraintViolationWithTemplate("Ngày đặt lịch phải nằm trong tương lai")
                    .addPropertyNode("bookingDate")
                    .addConstraintViolation();
            return false;
        }

        LocalDateTime bookingDateTime = LocalDateTime.of(dto.getBookingDate(), dto.getBookingTime());

        if(bookingDateTime.isBefore(LocalDateTime.now())) {
            context.buildConstraintViolationWithTemplate("Giờ đặt lịch phải nằm trong tương lai")
                    .addPropertyNode("bookingTime")
                    .addConstraintViolation();
            return false;
        }

    return true;
    }
}
