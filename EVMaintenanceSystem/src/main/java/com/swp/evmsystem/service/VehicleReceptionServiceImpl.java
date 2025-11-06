package com.swp.evmsystem.service;

public class VehicleReceptionServiceImpl {
    private final VehicleReceptionRepository receptionRepository;
    private final EmployeeRepository employeeRepository;

    // Service pricing (in VND)
    private static final double REGULAR_MAINTENANCE_COST = 500000;
    private static final double COMPONENT_REPLACEMENT_COST = 1500000;
    private static final double TECHNICAL_REPAIR_COST = 2000000;

    @Override
    @Transactional
    public VehicleReceptionResponseDTO createReception(VehicleReceptionRequestDTO request) {
        // Validate technician exists
        EmployeeEntity technician = employeeRepository.findById(request.getTechnicianId())
                .orElseThrow(() -> new ResourceNotFoundException("Technician not found with id: " + request.getTechnicianId()));

        // Calculate total cost
        double totalCost = calculateTotalCost(request.getServices());

        // Convert services list to comma-separated string
        String servicesString = String.join(",", request.getServices());
        // Create entity
        VehicleReceptionEntity entity = VehicleReceptionEntity.builder()
                .customerName(request.getCustomerName())
                .customerPhone(request.getCustomerPhone())
                .customerEmail(request.getCustomerEmail())
                .vehicleModel(request.getVehicleModel())
                .licensePlate(request.getLicensePlate())
                .vehicleColor(request.getVehicleColor())
                .vin(request.getVin())
                .mileage(request.getMileage())
                .services(servicesString)
                .assignedTechnician(technician)
                .notes(request.getNotes())
                .totalCost(totalCost)
                .status("RECEIVED")
                .build();

        // Save to database
        VehicleReceptionEntity savedEntity = receptionRepository.save(entity);

        // Convert to response DTO
        return convertToResponseDTO(savedEntity);
    }
    @Override
    public VehicleReceptionResponseDTO getReceptionById(Integer receptionId) {
        VehicleReceptionEntity entity = receptionRepository.findById(receptionId)
                .orElseThrow(() -> new ResourceNotFoundException("Reception not found with id: " + receptionId));
        return convertToResponseDTO(entity);
    }

    @Override
    public List<VehicleReceptionResponseDTO> getAllReceptions() {
        return receptionRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleReceptionResponseDTO> getReceptionsByTechnician(Integer technicianId) {
        return receptionRepository.findByAssignedTechnicianId(technicianId).stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public VehicleReceptionResponseDTO updateReceptionStatus(Integer receptionId, String status) {
        VehicleReceptionEntity entity = receptionRepository.findById(receptionId)
                .orElseThrow(() -> new ResourceNotFoundException("Reception not found with id: " + receptionId));

        entity.setStatus(status);
        VehicleReceptionEntity updatedEntity = receptionRepository.save(entity);

        return convertToResponseDTO(updatedEntity);
    }

    // Helper methods

    private double calculateTotalCost(List<String> services) {
        double total = 0.0;

        for (String service : services) {
            switch (service.toLowerCase()) {
                case "regularmaintenance":
                case "bao_duong_dinh_ky":
                    total += REGULAR_MAINTENANCE_COST;
                    break;
                case "componentreplacement":
                case "thay_the_phu_tung":
                    total += COMPONENT_REPLACEMENT_COST;
                    break;
                case "technicalrepair":
                case "sua_chua_ky_thuat":
                    total += TECHNICAL_REPAIR_COST;
                    break;
                default:
                    break;
            }
        }
        return total;
    }
