java
package com.swp.evmsystem.service.address;

import com.swp.evmsystem.entity.address.AddressEntity;

import java.util.List;
import java.util.Optional;

public interface AddressEntityService {
    AddressEntity createAddress(AddressEntity addressEntity);
    Optional<AddressEntity> findById(Long id);
    List<AddressEntity> findAll();
    void deleteById(Long id);
}