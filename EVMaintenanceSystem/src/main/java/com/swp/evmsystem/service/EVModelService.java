package com.swp.evmsystem.service;

import com.swp.evmsystem.entity.EVModelEntity;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public interface EVModelService {
    void createEVModel(EVModelEntity entity);
}
