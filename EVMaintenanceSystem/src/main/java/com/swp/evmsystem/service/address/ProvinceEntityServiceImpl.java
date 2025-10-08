package com.swp.evmsystem.service.address;

import com.swp.evmsystem.entity.address.ProvinceEntity;
import com.swp.evmsystem.repository.address.ProvinceEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProvinceEntityServiceImpl implements ProvinceEntityService {
    @Autowired
    ProvinceEntityRepository provinceEntityRepository;

    @Override
    public void createProvince(ProvinceEntity provinceEntity) {
        provinceEntityRepository.save(provinceEntity);
    }
}
