package com.swp.evmsystem.service;

import com.swp.evmsystem.dto.response.ServiceCenterDTO;
import com.swp.evmsystem.entity.ServiceCenterEntity;
import com.swp.evmsystem.mapper.ServiceCenterMapper;
import com.swp.evmsystem.repository.ServiceCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceCenterServiceImpl implements ServiceCenterService {
    @Autowired
    ServiceCenterRepository repository;
    @Autowired
    private ServiceCenterMapper mapper;

    @Override
    public ServiceCenterEntity getServiceCenterEntityById(int id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public boolean create(ServiceCenterEntity sve) {
        return repository.save(sve) != null;
    }

    @Override
    public List<ServiceCenterEntity> getServiceCenterEntities() {
        List<ServiceCenterEntity> serviceCenterEntities = repository.findAll();
        return serviceCenterEntities;
    }

    @Override
    public List<ServiceCenterDTO> getAllServiceCenters() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)   // chuyển từng Entity → DTO
                .collect(Collectors.toList());
    }
}
