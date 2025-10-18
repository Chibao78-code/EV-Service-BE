package com.swp.evmsystem.service;

import com.swp.evmsystem.dto.response.EvModelDTO;
import com.swp.evmsystem.entity.EvModelEntity;
import com.swp.evmsystem.mapper.EvModelMapper;
import com.swp.evmsystem.repository.EvModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EvModelServiceImpl implements EvModelService {
    @Autowired
    EvModelRepository repository;
    @Autowired
    EvModelMapper mapper;

    @Override
    public void createEVModel(EvModelEntity entity) {
        repository.save(entity);
    }

    @Override
    public EvModelEntity getModelById(int i) { // Retrieve an EV model by its ID
        return repository.findById(i).orElse(null); // Find the EV model entity by its ID and return it, or return null if not found
    }

    @Override
    public List<EvModelDTO> getAllModels() {
        return repository.findAll()// Retrieve all EV model entities from the repository
                .stream()
                .map(mapper::toDTO)
                .collect(Collectors.toList()); // Convert each entity to a DTO and collect them into a list
    }
}
