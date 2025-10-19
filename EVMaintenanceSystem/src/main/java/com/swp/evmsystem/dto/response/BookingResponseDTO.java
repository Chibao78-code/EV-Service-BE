package com.swp.evmsystem.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingResponseDTO {
    String code;
    String center;
    String address;
    String date;
    String time;
    String eVModel;
}
