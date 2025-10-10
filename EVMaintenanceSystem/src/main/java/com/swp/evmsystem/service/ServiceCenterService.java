package com.swp.evmsystem.service;

import com.swp.evmsystem.dto.response.ServiceCenterDTO;
import com.swp.evmsystem.entity.ServiceCenterEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ServiceCenterService {
    boolean create(ServiceCenterEntity sve);
    List<ServiceCenterDTO> getAllServiceCenters();
}
