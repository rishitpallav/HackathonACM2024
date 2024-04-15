package com.iit.shareameal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iit.shareameal.model.Leftover;
import org.springframework.stereotype.Repository;

@Repository
public interface LeftoverRepository extends JpaRepository<Leftover, Integer> {

}
