package com.swp.evmsystem.security;

import com.swp.evmsystem.entity.UserEntity;
import com.swp.evmsystem.enums.UserStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserEntityDetails implements UserDetails {
    private UserEntity userEntity;

    public UserEntityDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // provide role as authority
        if (userEntity == null || userEntity.getRole() == null) {
            return List.of();
        }
        // role name provided by entity (keeps original behavior)
        return List.of(new SimpleGrantedAuthority("ROLE_" + userEntity.getRole().getName()));
    }

    @Override
    public String getPassword() {
        return userEntity != null ? userEntity.getPassword() : null;
    }

    @Override
    public String getUsername() {
        if (userEntity == null) {
            return null;
        }
        // prioritize phone number as username if available
        if (userEntity.getPhone() != null && !userEntity.getPhone().isBlank()) {
            return userEntity.getPhone();
        } else {
            return userEntity.getEmail();
        }
    }

    public int getId() {
        return userEntity != null ? userEntity.getId() : -1;
    }

    public UserEntity getUser() {
        return userEntity;
    } 

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return userEntity.getStatus() == UserStatus.ACTIVE;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return userEntity.getStatus() == UserStatus.ACTIVE;
    }
}
