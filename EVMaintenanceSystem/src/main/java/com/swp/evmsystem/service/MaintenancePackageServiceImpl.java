package com.swp.evmsystem.service;

@Service
public class MaintenancePackageServiceImpl implements MaintenancePackageService {

    @Autowired
    private MaintenancePackageRepository maintenancePackageRepository;

    @Override
    public MaintenancePackageEntity createPackage(MaintenancePackageEntity packageEntity) {
        return maintenancePackageRepository.save(packageEntity);
    }

    @Override
    public List<MaintenancePackageEntity> getAllPackages() {
        return maintenancePackageRepository.findAll();
    }

    @Override
    public List<MaintenancePackageEntity> getPackagesByOfferType(Integer offerTypeId) {
        return maintenancePackageRepository.findByOfferType_OfferTypeId(offerTypeId);
    }
}