package com.mhawi.slider.business.service;

import com.mhawi.slider.business.model.dto.UserRegistrationDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;

@Component
public class DataInitializer implements Serializable {

    @Autowired
    private UserService userService;

    @PostConstruct
    public void init() {

        try {
            if (userService.countUsers() == 0) {
                UserRegistrationDto user = new UserRegistrationDto();
                user.setFirstName("malek");
                user.setLastName("mhawi");
                user.setUsername("admin");
                user.setPassword("admin@Mhawi");
                user.setEmail("admin@admin.com");
                userService.save(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}