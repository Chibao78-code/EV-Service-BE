package com.swp.evmsystem.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "nhan_vien")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeEntity extends  UserEntity {
    @Column(name = "full_name", length = 100, nullable = false)
    private String fullName;
    @ManyToOne
    @JoinColumn(name = "center_id")
    private ServiceCenterEntity centerId;
}
