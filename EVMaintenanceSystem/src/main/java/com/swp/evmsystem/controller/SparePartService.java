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

    // Admin endpoints
    @PostMapping
    // @PreAuthorize("hasAnyRole('STAFF', 'ADMIN')")
    public ResponseEntity<?> createSparePart(@Valid @RequestBody SparePartRequestDTO request) { // chức năng tạo phụ tùng
        try {
            System.out.println("=== CREATE SPARE PART START ===");
            System.out.println("Request: " + request);

            SparePartResponseDTO created = sparePartService.createSparePartDTO(request);  // Implement this method in the service layer

            System.out.println("Spare part created: " + created.getPartId()); // Log the ID of the created spare part
            System.out.println("=== CREATE SPARE PART END ===");

            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (Exception e) {
            System.err.println("ERROR creating spare part: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", e.getMessage()));
        }
    }
}
