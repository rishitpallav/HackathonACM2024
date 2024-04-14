package com.iit.shareameal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iit.shareameal.model.Shelter;

public interface ShelterRepository extends JpaRepository<Shelter, Integer> {

    Shelter findByEmail(String email);
}
