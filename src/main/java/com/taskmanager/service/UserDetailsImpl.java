package com.taskmanager.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.taskmanager.model.User;

import java.util.Collection;
import java.util.Collections;

public record UserDetailsImpl(User user) implements UserDetails {
    public Collection<? extends GrantedAuthority> getAuthorities(){
        return Collections.emptyList();
    }
    @Override
    public String getUsername(){
        return user.getUsername();
    }
    @Override
    public String getPassword(){
        return user.getPassword();
    }
    @Override
    public boolean isAccountNonExpired(){
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired(){
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isEnabled(){
        return true;
    }

}
