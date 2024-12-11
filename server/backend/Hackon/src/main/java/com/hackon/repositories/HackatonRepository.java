package com.hackon.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hackon.entities.Hackathon;

public interface HackatonRepository extends JpaRepository<Hackathon, Long>{

}
