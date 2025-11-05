package com.swp.evmsystem.controller;


@RestController
@RequestMapping("/api/maintenance-packages")
public class MaintenancePackageController {

    @Autowired
    private MaintenancePackageService maintenancePackageService;

    @GetMapping
    public ResponseEntity<List<MaintenancePackageDTO>> getAllMaintenancePackages() {
        List<MaintenancePackageDTO> packages = maintenancePackageService.getAllPackages();
        return ResponseEntity.ok(packages);
    }


}