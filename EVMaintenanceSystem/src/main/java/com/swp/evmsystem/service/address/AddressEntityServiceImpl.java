package com.swp.evmsystem.service.address;

import com.swp.evmsystem.entity.address.AddressEntity;
import com.swp.evmsystem.repository.address.AddressEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressEntityServiceImpl implements AddressEntityService {
    @Autowired
    AddressEntityRepository addressEntityRepository;

    @Override
    public void createAddress(AddressEntity addressEntity) {
        // Save the address entity to the database
        addressEntityRepository.save(addressEntity);
    }

    @Override
    // Retrieve an address by its ID
    public AddressEntity getAddressById(int id) {
        // Find the address entity by its ID and return it, or return null if not found
        return addressEntityRepository.findById(id).orElse(null);

    }

}
