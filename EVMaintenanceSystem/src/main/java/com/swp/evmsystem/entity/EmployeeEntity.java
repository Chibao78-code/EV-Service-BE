package com.swp.evmsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "nhan_vien")
@Getter
@Setter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class EmployeeEntity extends UserEntity {
    @Column(name = "full_name", length = 100, nullable = false)
    String fullName;
    @ManyToOne
    @JoinColumn(name = "center_id")
    ServiceCenterEntity centerId;

}
