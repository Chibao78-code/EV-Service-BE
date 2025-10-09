package com.swp.evmsystem.service;

import com.swp.evmsystem.entity.EvModelEntity;
import com.swp.evmsystem.repository.EvModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EvModelServiceImpl implements EvModelService {
    @Autowired
    EvModelRepository repository;

    @Override
    public void createEVModel(EvModelEntity entity) {
        repository.save(entity);
    }

    @Override
    public EvModelEntity getModelById(int i) {
        return repository.findById(i).get();
    }
}
