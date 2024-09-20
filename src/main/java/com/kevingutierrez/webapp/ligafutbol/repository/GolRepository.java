package com.kevingutierrez.webapp.ligafutbol.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kevingutierrez.webapp.ligafutbol.model.Gol;

public interface GolRepository extends JpaRepository <Gol, Long> {

}
