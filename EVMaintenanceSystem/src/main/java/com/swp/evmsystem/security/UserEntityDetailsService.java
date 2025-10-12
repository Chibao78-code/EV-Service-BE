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
    @Autowired
    public UserEntityDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Attempt lookup by phone first to minimize calls.
        Optional<UserEntity> user = userRepository.findByPhone(username);
        if (user.isEmpty()) {
            // Fallback: try lookup by email if phone lookup failed.
            user = userRepository.findByEmail(username);
        }

        // Convert found entity to UserDetails or throw if not present.
        return user.map(UserEntityDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with this phone or email: " + username));
    }

    public UserEntityDetails loadUserById(Integer id) throws UsernameNotFoundException {
        // Use orElseThrow for concise error handling and to avoid explicit Optional checks.
        UserEntity user = userRepository.findById(id)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with this id: " + id));
        return new UserEntityDetails(user);
    }
}