package com.swp.evmsystem.security;

import com.swp.evmsystem.entity.UserEntity;
import com.swp.evmsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserEntityDetailsService implements UserDetailsService {
    @Autowired
    private final UserRepository userRepository;

    public UserEntityDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByPhone(username).isEmpty() ? userRepository.findByEmail(username) : userRepository.findByPhone(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with this phone or email: " + username);
        }
        return new UserEntityDetails(user.get());
    }

    public UserEntityDetails loadUserById(Integer id) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with this id: " + id);
        }
        return new UserEntityDetails(user.get());
    }
}