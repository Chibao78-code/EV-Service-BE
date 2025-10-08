package com.swp.evmsystem.repository.address;

import com.swp.evmsystem.entity.address.DistrictEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DistrictEntityRepository extends CrudRepository<DistrictEntity,Integer> {
    DistrictEntity save(DistrictEntity districtEntity);

    DistrictEntity findDistrictEntitiesByDistrictName(String districtName);
}
