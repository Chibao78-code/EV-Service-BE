package com.swp.evmsystem.entity.address;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "quan_thanh")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class DistrictEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "district_id")
    Integer districtId;
    @Column(name = "district_name", length = 50, nullable = false)
    String districtName;
    @JoinColumn(name = "province_id")
    @ManyToOne(fetch = FetchType.LAZY)
    ProvinceEntity province;
}
