package com.swp.evmsystem.service;

import com.swp.evmsystem.entity.CustomerEntity;
import com.swp.evmsystem.repository.CustomerRepository;
import com.swp.evmsystem.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository;

}
