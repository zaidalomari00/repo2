package com.mhawi.slider.business.repo;

import com.mhawi.slider.business.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long>{
    User findByEmail(String email);
}