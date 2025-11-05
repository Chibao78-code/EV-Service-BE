package com.swp.evmsystem.repository;

@Repository
public interface MaintenancePackageRepository extends JpaRepository<MaintenancePackageEntity, Integer> {
    List<MaintenancePackageEntity> findByOfferType_OfferTypeId(Integer offerTypeId);
}
