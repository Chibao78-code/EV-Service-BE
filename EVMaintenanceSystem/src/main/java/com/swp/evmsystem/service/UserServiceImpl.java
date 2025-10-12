package com.swp.evmsystem.service;

import com.swp.evmsystem.entity.UserEntity;
import com.swp.evmsystem.mapper.UserMapper;
import com.swp.evmsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository repository;
    @Autowired
    private UserMapper mapper;

    @Override
    public void createUser(UserEntity user) {
        repository.save(user);
    }

    @Override
    public int getAllCount() {
        return repository.findAll().size();
    }
}
