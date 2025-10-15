package com.swp.evmsystem.service.address;

import com.swp.evmsystem.entity.address.DistrictEntity;
import org.springframework.stereotype.Service;

@Service
public interface DistrictEntityService {
    void createDistrict(DistrictEntity districtEntity);

    DistrictEntity findDistrictEntityByName(String districtName);

    DistrictEntity findDistrictEntityById(Integer id);


    void deleteAll();

    void deleteById(Integer id);

    void updateDistrict(DistrictEntity districtEntity);
    boolean existsById(Integer id);
    boolean existsByName(String name);
    boolean existsByProvince_ProvinceId(Integer provinceId);


}
