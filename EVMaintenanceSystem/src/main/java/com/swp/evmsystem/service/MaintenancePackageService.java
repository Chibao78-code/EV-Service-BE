package com.swp.evmsystem.service;

@Service
public interface MaintenancePackageService {
    MaintenancePackageEntity createPackage(MaintenancePackageEntity packageEntity);
    List<MaintenancePackageEntity> getAllPackages();
    List<MaintenancePackageEntity> getPackagesByOfferType(Integer offerTypeId);
}
