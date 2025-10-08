package com.swp.evmsystem.service;

import com.swp.evmsystem.entity.EVModelEntity;
import com.swp.evmsystem.repository.EVModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EVModelServiceImpl implements EVModelService {
    @Autowired
    private EVModelRepository repository;

    @Override
    public void createEVModel(EVModelEntity entity) {
        repository.save(entity);
    }
}
