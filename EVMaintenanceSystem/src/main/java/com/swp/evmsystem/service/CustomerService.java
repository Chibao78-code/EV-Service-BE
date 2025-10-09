package com.swp.evmsystem.service;

import com.swp.evmsystem.entity.CustomerEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CustomerService {
    CustomerEntity getCustomerById(Integer id);
}
