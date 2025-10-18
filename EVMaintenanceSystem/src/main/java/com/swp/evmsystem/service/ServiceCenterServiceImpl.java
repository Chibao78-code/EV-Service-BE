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
    public List<ServiceCenterEntity> getServiceCenterEntities() { // Lấy tất cả ServiceCenterEntity
        List<ServiceCenterEntity> serviceCenterEntities = repository.findAll(); // Lấy tất cả các thực thể từ repository
        return serviceCenterEntities; // Trả về danh sách các thực thể
    }

    @Override
    public List<ServiceCenterDTO> getAllServiceCenters() { // Lấy tất cả ServiceCenter và chuyển đổi sang DTO
        return repository.findAll() // Lấy tất cả các thực thể từ repository
                .stream()
                .map(mapper::toDTO)   // chuyển từng Entity → DTO
                .collect(Collectors.toList());
    }
}
