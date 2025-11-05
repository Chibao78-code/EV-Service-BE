package com.swp.evmsystem.controller;


@RestController
@RequestMapping("/api/maintenance-packages")
public class MaintenancePackageController {

    @Autowired
    private MaintenancePackageService maintenancePackageService;

    @GetMapping
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<MaintenancePackageEntity>> getAllPackages() {
        List<MaintenancePackageEntity> packages = maintenancePackageService.getAllPackages();
        return new ResponseEntity<>(packages, HttpStatus.OK);
    }

    @GetMapping("/by-offer-type/{offerTypeId}")
    @PreAuthorize("permitAll()")
    public ResponseEntity<List<MaintenancePackageEntity>> getPackagesByOfferType(@PathVariable Integer offerTypeId) {
        List<MaintenancePackageEntity> packages = maintenancePackageService.getPackagesByOfferType(offerTypeId);
        return new ResponseEntity<>(packages, HttpStatus.OK);
    }
}
}