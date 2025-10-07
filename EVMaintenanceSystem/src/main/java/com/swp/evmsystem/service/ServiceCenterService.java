package com.swp.evmsystem.service;

import com.swp.evmsystem.entity.ServiceCenterEntity;
import org.springframework.stereotype.Service;

@Service
public interface ServiceCenterService {
    ServiceCenterEntity getServiceCenterEntityById(int id);
    boolean create(ServiceCenterEntity sve);
}
