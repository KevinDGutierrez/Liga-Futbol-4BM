package com.kevingutierrez.webapp.ligafutbol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kevingutierrez.webapp.ligafutbol.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
