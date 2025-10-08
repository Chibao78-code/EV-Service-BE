package com.swp.evmsystem.repository.address;

import com.swp.evmsystem.entity.address.AddressEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressEntityRepository extends JpaRepository<AddressEntity,Integer> {
}
