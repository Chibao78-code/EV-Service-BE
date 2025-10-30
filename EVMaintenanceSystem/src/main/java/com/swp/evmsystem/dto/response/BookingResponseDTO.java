package com.swp.evmsystem.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BookingResponseDTO {
    String code;
    String customerName;
    String customerPhone;
    String customerEmail;

    String center;
    String address;
    String date;
    String time;

    String eVModel;

    String offerType;
    String item;
}
