package com.swp.evmsystem.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.swp.evmsystem.validation.annotation.ValidBookingDateTime;
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
    Integer eVId;
    Integer centerId;
    LocalDate bookingDate;
    LocalTime bookingTime;
}
