package com.swp.evmsystem.service;

import com.swp.evmsystem.entity.ServiceCenterEntity;
import com.swp.evmsystem.repository.ServiceCenterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceCenterServiceImpl implements ServiceCenterService {
    @Autowired
    ServiceCenterRepository serviceCenterRepository;

    @Override
    public ServiceCenterEntity getServiceCenterEntityById(int id) {
        return serviceCenterRepository.findById(id);
    }
}
