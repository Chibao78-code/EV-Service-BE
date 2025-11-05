package com.swp.evmsystem.repository;

@Repository
public interface VehicleReceptionRepository extends JpaRepository<VehicleReceptionEntity, Integer> {
    List<VehicleReceptionEntity> findByStatus(String status);

    List<VehicleReceptionEntity> findByCustomerPhone(String customerPhone);

    List<VehicleReceptionEntity> findByLicensePlate(String licensePlate);

    List<VehicleReceptionEntity> findByAssignedTechnicianId(Integer technicianId);
}
