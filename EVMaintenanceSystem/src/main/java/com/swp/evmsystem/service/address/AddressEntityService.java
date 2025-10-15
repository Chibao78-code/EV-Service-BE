package com.swp.evmsystem.service.address;

import com.swp.evmsystem.entity.address.AddressEntity;
import org.springframework.stereotype.Service;

@Service
public interface AddressEntityService {
    void createAddress(AddressEntity addressEntity);
    AddressEntity getAddressById(Integer id);
    AddressEntity updateAddress(AddressEntity addressEntity);
    void deleteAddress(Integer id);

}
