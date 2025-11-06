package com.swp.evmsystem.service;

public class VehicleReceptionService {
    VehicleReceptionResponseDTO createReception(VehicleReceptionRequestDTO request);

    VehicleReceptionResponseDTO getReceptionById(Integer receptionId);

    List<VehicleReceptionResponseDTO> getAllReceptions();

    List<VehicleReceptionResponseDTO> getReceptionsByStatus(String status);

    List<VehicleReceptionResponseDTO> getReceptionsByTechnician(Integer technicianId);

    VehicleReceptionResponseDTO updateReceptionStatus(Integer receptionId, String status);
}
