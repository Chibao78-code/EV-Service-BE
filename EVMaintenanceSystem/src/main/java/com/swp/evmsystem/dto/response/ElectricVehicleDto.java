package com.swp.evmsystem.dto.response;

import com.swp.evmsystem.entity.ElectricVehicleEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ElectricVehicleDto {
    int id;
    String licensePlate;
    String model;
    String VIN;

    public ElectricVehicleDto(ElectricVehicleEntity electricVehicleEntity) {
        this.id = electricVehicleEntity.getId();
        this.licensePlate = electricVehicleEntity.getLicensePlate();
        this.model = electricVehicleEntity.getModel();
        this.VIN = electricVehicleEntity.getVIN();
    }
}
