package com.swp.evmsystem.service;

import com.swp.evmsystem.dto.response.OfferTypeDTO;
import com.swp.evmsystem.entity.OfferTypeEntity;
import com.swp.evmsystem.mapper.OfferTypeMapper;
import com.swp.evmsystem.repository.OfferTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferTypeServiceImpl implements  OfferTypeService {
    private final OfferTypeRepository repository;
    private final OfferTypeMapper offerTypeMapper;

    @Autowired
    public OfferTypeServiceImpl(OfferTypeRepository repository, OfferTypeMapper offerTypeMapper) { // Constructor injection
        this.repository = repository; // Injecting OfferTypeRepository
        this.offerTypeMapper = offerTypeMapper; // Injecting OfferTypeMapper
    }

    @Override
    public List<OfferTypeDTO> findAll() { // Retrieve all offer types
        return repository.findAll().stream()
                .map(offerTypeMapper::toDTO)
                .collect(Collectors.toList()); // Convert entities to DTOs
    }

    @Override // Create a new offer type
    public void createOfferType(OfferTypeEntity offerTypeEntity) {
        repository.save(offerTypeEntity);
    }
}
