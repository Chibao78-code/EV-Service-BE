java
package com.swp.evmsystem.service.address;

import com.swp.evmsystem.entity.address.AddressEntity;
import com.swp.evmsystem.repository.address.AddressEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AddressEntityServiceImpl implements AddressEntityService {

    private final AddressEntityRepository addressEntityRepository;

    @Override
    public AddressEntity createAddress(AddressEntity addressEntity) {
        return addressEntityRepository.save(addressEntity);
    }
    @Override
    public Optional<AddressEntity> findById(Long id) {
        return addressEntityRepository.findById(id);
    }

    

}