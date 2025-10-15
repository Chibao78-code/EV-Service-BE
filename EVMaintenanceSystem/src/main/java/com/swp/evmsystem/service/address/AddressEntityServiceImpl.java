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

        addressEntityRepository.save(addressEntity);
    }

    @Override
    public AddressEntity getAddressById(int id) {
        return addressEntityRepository.findById(id).orElse(null);

    }

}
