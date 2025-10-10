package com.swp.evmsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "khach_hang")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@SuperBuilder
public class CustomerEntity extends UserEntity {
    @OneToMany(mappedBy = "owner")
    List<ElectricVehicleEntity> vehicle = new ArrayList<ElectricVehicleEntity>();
}
