package com.mhawi.slider.business.service;

import com.mhawi.slider.business.model.User;
import com.mhawi.slider.business.model.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
    long countUsers();
}