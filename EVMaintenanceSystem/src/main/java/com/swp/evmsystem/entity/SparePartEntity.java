package com.swp.evmsystem.entity;

import com.swp.evmsystem.enums.PartCategory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Table(name = "phu_tung")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class SparePartEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "spare_part_id")
    Integer sparePartId;
    @Column(name = "spare_part_name")
    String sparePartName;
    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    private PartCategory category;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinTable(name = "model_id" )
    EvModelEntity model;
}
