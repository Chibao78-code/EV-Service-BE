package com.swp.evmsystem.entity;

@Entity
@Table(name = "tiep_nhan_xe")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
@Builder
public class VehicleReceptionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reception_id")
    Integer receptionId;

    // Customer information
    @Column(name = "customer_name", length = 100, nullable = false)
    String customerName;

    @Column(name = "customer_phone", length = 10, nullable = false)
    String customerPhone;

    @Column(name = "customer_email", length = 100)
    String customerEmail;
}
