package com.iit.shareameal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iit.shareameal.model.Donor;

public interface DonorRepository extends JpaRepository<Donor, Integer> {

    Donor findByEmail(String email);
}
