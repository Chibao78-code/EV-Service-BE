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

    }
