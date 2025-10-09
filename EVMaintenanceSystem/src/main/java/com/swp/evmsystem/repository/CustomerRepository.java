package com.swp.evmsystem.repository;

import com.swp.evmsystem.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerEntity, Integer> {
    CustomerEntity getCustomerEntityById(int id);
}
