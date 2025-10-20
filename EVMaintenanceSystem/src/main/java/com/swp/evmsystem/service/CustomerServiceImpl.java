package com.swp.evmsystem.service;

import com.swp.evmsystem.entity.CustomerEntity;
import com.swp.evmsystem.repository.CustomerRepository;
import com.swp.evmsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository repository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public CustomerEntity getCustomerById(Integer id) { // Method to get customer by ID
        return repository.findById(id) // Find customer by ID
                .orElseThrow(() -> new NoSuchElementException("Customer not found with id: " + id)); // Throw exception if not found
    }
}
