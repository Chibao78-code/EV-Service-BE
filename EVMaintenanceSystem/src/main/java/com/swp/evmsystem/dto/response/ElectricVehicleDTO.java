package com.swp.evmsystem.dto.response;

import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class ElectricVehicleDTO {
    int id;
    String model;
    String vin;
    String licensePlate;
}
