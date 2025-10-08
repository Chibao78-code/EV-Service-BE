package com.swp.evmsystem.service.address;

import com.swp.evmsystem.entity.address.ProvinceEntity;
import org.springframework.stereotype.Service;

@Service
public interface ProvinceEntityService {
    void createProvince(ProvinceEntity provinceEntity);
}
