package com.swp.evmsystem.security;

import com.swp.evmsystem.dto.response.UserInforDTO;
import com.swp.evmsystem.entity.CustomerEntity;
import com.swp.evmsystem.entity.EmployeeEntity;
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
    private UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UserEntity> user = userRepository.findByPhone(username).isEmpty() ? userRepository.findByEmail(username) : userRepository.findByPhone(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found with this phone or email: " + username);
        }
        return new UserEntityDetails(user.get());
    }

    public UserInforDTO getUserByUserDetail(UserEntityDetails userDetail) {
        UserEntity user = userDetail.getUserEntity();
        UserInforDTO.UserInforDTOBuilder builder = UserInforDTO.builder().role(user.getRole().name());

        if(user instanceof CustomerEntity customer) {
            builder.fullName(customer.getFullName());
        } else if (user instanceof EmployeeEntity employee) {
            builder.fullName(employee.getFullName());
        }

        return builder.build();
    }
}