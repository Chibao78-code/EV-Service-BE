package com.swp.evmsystem.repository.address;

import com.swp.evmsystem.entity.address.ProvinceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceEntityRepository extends JpaRepository<ProvinceEntity, Integer> {
    ProvinceEntity save(ProvinceEntity provinceEntity);
}
