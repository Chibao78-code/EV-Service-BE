package com.swp.evmsystem.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Setter
@Getter
@Builder
public class ServiceCenterDTO {
    private int id;
    private String centerName;
    private String centerAddress;
    private String centerPhone;
    private LocalTime startTime;
    private LocalTime endTime;
}
