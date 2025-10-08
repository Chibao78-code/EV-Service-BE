package com.swp.evmsystem.service.address;

import com.swp.evmsystem.entity.address.DistrictEntity;
import com.swp.evmsystem.entity.address.WardEntity;
import com.swp.evmsystem.repository.address.WardEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WardEntityServiceImpl implements WardEntityService {
    @Autowired
    private WardEntityRepository wardEntityRepository;


    @Override
    public void createWard(WardEntity wardEntity) {
        wardEntityRepository.save(wardEntity);
    }

    @Override
    public WardEntity findWardEntityByWardNameAndDistrict(String wardName, DistrictEntity district) {
        return wardEntityRepository.findWardEntitiesByWardNameAndDistrict(wardName, district);
    }
}
