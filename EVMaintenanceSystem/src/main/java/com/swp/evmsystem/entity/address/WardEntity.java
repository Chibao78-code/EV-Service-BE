package com.swp.evmsystem.entity.address;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "phuong_xa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class WardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ward_id")
    Integer wardId;
    @Column(name = "ward_name", length =  50, nullable = false)
    String wardName;
    @JoinColumn(name = "district_id")
    @ManyToOne(fetch = FetchType.LAZY)
    DistrictEntity district;
}