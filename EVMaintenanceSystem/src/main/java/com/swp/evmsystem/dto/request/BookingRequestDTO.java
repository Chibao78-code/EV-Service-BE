package com.swp.evmsystem.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.swp.evmsystem.validation.annotation.ValidBookingDateTime;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ValidBookingDateTime
public class BookingRequestDTO {
    @JsonProperty("eVId")
    @Min(value = 1, message = "Id phải là số nguyên lớn hơn 0")
    Integer eVId;
    @Min(value = 1, message = "Id phải là số nguyên lớn hơn 0")
    Integer centerId;
    LocalDate bookingDate;
    LocalTime bookingTime;
}
