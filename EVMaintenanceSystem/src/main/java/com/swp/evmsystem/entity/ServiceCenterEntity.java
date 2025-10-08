package com.swp.evmsystem.entity;

import com.swp.evmsystem.entity.address.AddressEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "trung_tam")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class ServiceCenterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "center_id")
    int id;
    @Column(name = "center_name", length = 100, nullable = false)
    String centerName;
    @Column(name = "center_phone", length = 12, nullable = false)
    String centerPhone;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "address_id")
    AddressEntity centerAddress;
}
