package com.swp.evmsystem.controller;

@RestController
@RequestMapping("/api/spare-parts")
public class SparePartController {

    @Autowired
    private SparePartService sparePartService;

    @GetMapping
    // @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    public ResponseEntity<List<SparePartResponseDTO>> getAllSpareParts() {
        try {
            List<SparePartResponseDTO> spareParts = sparePartService.getAllSparePartsDTO();
            return ResponseEntity.ok(spareParts);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Statistics endpoint
    @GetMapping("/statistics")
    // @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    public ResponseEntity<SparePartStatsDTO> getStatistics() { // chức năng thống kê
        try {
            SparePartStatsDTO stats = sparePartService.getStatistics(); // Implement this method in the service layer
            return ResponseEntity.ok(stats);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
