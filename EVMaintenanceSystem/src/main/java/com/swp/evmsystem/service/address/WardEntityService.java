package com.swp.evmsystem.service.address;

import com.swp.evmsystem.entity.address.DistrictEntity;
import com.swp.evmsystem.entity.address.WardEntity;
import org.springframework.stereotype.Service;

@Service
public interface WardEntityService {
    void createWard(WardEntity wardEntity);

    WardEntity findWardEntityByWardNameAndDistrict(String wardName, DistrictEntity district);
}
