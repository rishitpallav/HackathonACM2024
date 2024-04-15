package com.iit.shareameal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iit.shareameal.model.Shelter;
import org.springframework.stereotype.Repository;

@Repository
public interface ShelterRepository extends JpaRepository<Shelter, Integer> {

    Shelter findByEmail(String email);
}
