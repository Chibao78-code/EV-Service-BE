package com.swp.evmsystem.service;

import com.swp.evmsystem.entity.EvModelEntity;
import org.springframework.stereotype.Service;

@Service
public interface EvModelService {
    void createEVModel(EvModelEntity entity);
}
