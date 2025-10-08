package com.swp.evmsystem.repository.address;

import com.swp.evmsystem.entity.address.DistrictEntity;
import com.swp.evmsystem.entity.address.WardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WardEntityRepository extends JpaRepository<WardEntity,Integer> {
    WardEntity findWardEntitiesByWardNameAndDistrictDistrictName(String wardName, String districtDistrictName);

    WardEntity findWardEntitiesByWardNameAndDistrict(String wardName, DistrictEntity district);
}
