package com.iit.shareameal.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.iit.shareameal.model.Food;

public interface FoodRepository extends JpaRepository<Food, Integer> {

}
