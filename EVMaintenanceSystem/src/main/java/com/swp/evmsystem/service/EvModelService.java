package com.swp.evmsystem.service;

import com.swp.evmsystem.dto.response.EvModelDTO;
import com.swp.evmsystem.entity.EvModelEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface EvModelService {
    void createEVModel(EvModelEntity entity);

    EvModelEntity getModelById(int i);

    List<EvModelDTO> getAllModels();
}
