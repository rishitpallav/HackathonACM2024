package com.iit.shareameal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iit.shareameal.model.Donor;
import com.iit.shareameal.model.Food;
import com.iit.shareameal.model.Leftover;
import com.iit.shareameal.model.Shelter;
import com.iit.shareameal.repository.FoodRepository;
import com.iit.shareameal.repository.LeftoverRepository;

public class DonorController {

    @Autowired
    private LeftoverRepository leftoverRepository;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private boolean isLoginDonor;

    @Autowired
    private Donor loginDonor;

    @Autowired
    private Shelter loginShelter;

    @PostMapping("/addLeftover")
    public ResponseEntity<?> addLeftover(@RequestParam int foodId, @RequestParam int quantity) {
        if (isLoginDonor) {
            Leftover leftover = new Leftover();
            leftover.setFoodId(foodId);
            leftover.setDonorId(loginDonor.getId());
            leftover.setQuantity(quantity);
            leftoverRepository.save(leftover);
            return ResponseEntity.ok("Leftover added");
        }
        return ResponseEntity.badRequest().body("Donor not logged in");
    }

    @PostMapping("/addFood")
    public ResponseEntity<?> addFood(@RequestParam Food food) {
        if (isLoginDonor) {
            foodRepository.save(food);
            return ResponseEntity.ok("Food added");
        }
        return ResponseEntity.badRequest().body("Donor not logged in");
    }

}
