package com.swp.evmsystem.service.address;

import com.swp.evmsystem.entity.address.DistrictEntity;
import com.swp.evmsystem.repository.address.DistrictEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DistrictEntityServiceImpl implements  DistrictEntityService {
    @Autowired
    DistrictEntityRepository districtEntityRepository;


    @Override
    public void createDistrict(DistrictEntity districtEntity) {
        districtEntityRepository.save(districtEntity);
    }

    @Override
    public DistrictEntity findDistrictEntityByName(String districtName) {
        return districtEntityRepository.findDistrictEntitiesByDistrictName(districtName);
    }

    @Override
    public DistrictEntity findDistrictEntityById(int id) {
        return districtEntityRepository.findById(id).orElse(null);
    }

    @Override
    public DistrictEntity findDistrictEntityByCode(String code) {
        return districtEntityRepository.findDistrictEntitiesByDistrictCode(code);
    }

}
