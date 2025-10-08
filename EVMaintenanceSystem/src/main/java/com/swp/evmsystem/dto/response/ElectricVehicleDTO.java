package com.swp.evmsystem.dto.response;

import com.swp.evmsystem.entity.ElectricVehicleEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class ElectricVehicleDTO {
    int id;
    String licensePlate;
    String model;
    String VIN;
}
