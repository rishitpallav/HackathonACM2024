package com.iit.shareameal.controller;

import com.iit.shareameal.model.Food;
import com.iit.shareameal.model.Leftover;
import com.iit.shareameal.repository.FoodRepository;
import com.iit.shareameal.repository.LeftoverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.iit.shareameal.model.Donor;
import com.iit.shareameal.model.Shelter;
import com.iit.shareameal.repository.DonorRepository;
import com.iit.shareameal.repository.ShelterRepository;

import java.util.Map;

@Controller
public class SessionController {
    private Donor loginDonor = new Donor();
    private Shelter loginShelter = new Shelter();
    private boolean isLoginDonor = false;

    public Donor getLoginDonor() {
        return loginDonor;
    }

    public Shelter getLoginShelter() {
        return loginShelter;
    }

    public boolean getIsLoginDonor() {
        return isLoginDonor;
    }

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private ShelterRepository shelterRepository;

    @Autowired
    private LeftoverRepository leftoverRepository;

    @Autowired
    private FoodRepository foodRepository;

    @PostMapping("/login/donor")
    public ResponseEntity<?> loginDonor(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        System.out.println("email: " + email +  " password: " + password);

        Donor donor = donorRepository.findByEmail(email);
        if (donor != null) {
            if (donor.getPassword().equals(password)) {
                loginDonor = donor;
                isLoginDonor = true;
                return ResponseEntity.ok(donor);
            }
        }
        return ResponseEntity.badRequest().body("Invalid credentials");
    }

    @GetMapping("/login/shelter")
    public ResponseEntity<?> loginShelter(@RequestParam String email, @RequestParam String password) {
        Shelter shelter = shelterRepository.findByEmail(email);
        if (shelter != null) {
            if (shelter.getPassword().equals(password)) {
                loginShelter = shelter;
                isLoginDonor = false;
                return ResponseEntity.ok(shelter);
            }
        }
        return ResponseEntity.badRequest().body("Invalid credentials");
    }

    @PostMapping("/register/donor")
    public ResponseEntity<?> registerDonor(@RequestBody Donor donor) {
        Donor existingDonor = donorRepository.findByEmail(donor.getEmail());
        if (existingDonor == null) {
            donorRepository.save(donor);
            loginDonor = donor;
            isLoginDonor = true;
            return ResponseEntity.ok(donor);
        }
        return ResponseEntity.badRequest().body("Donor already exists");
    }

    @PostMapping("/register/shelter")
    public ResponseEntity<?> registerShelter(@RequestParam Shelter shelter) {
        Shelter existingShelter = shelterRepository.findByEmail(shelter.getEmail());
        if (existingShelter == null) {
            shelterRepository.save(shelter);
            loginShelter = shelter;
            isLoginDonor = false;
            return ResponseEntity.ok(shelter);
        }
        return ResponseEntity.badRequest().body("Shelter already exists");
    }

    @GetMapping("/logout")
    public ResponseEntity<?> logout() {
        loginDonor = new Donor();
        loginShelter = new Shelter();
        isLoginDonor = false;
        return ResponseEntity.ok("Logged out");
    }

    @GetMapping("/session")
    public ResponseEntity<?> getSession() {
        if (isLoginDonor) {
            return ResponseEntity.ok(loginDonor);
        }
        return ResponseEntity.ok(loginShelter);
    }

    @PostMapping("/addLeftover")
    public ResponseEntity<?> addLeftover(@RequestBody Leftover leftover) {
        if (isLoginDonor) {
            leftover.setDonorId(loginDonor.getId());
            leftoverRepository.save(leftover);
            return ResponseEntity.ok("Leftover added");
        }
        return ResponseEntity.badRequest().body("Donor not logged in");
    }

    @PostMapping("/addFood")
    public ResponseEntity<?> addFood(@RequestBody Food food) {
        if (isLoginDonor) {
            foodRepository.save(food);
            return ResponseEntity.ok("Food added");
        }
        return ResponseEntity.badRequest().body("Donor not logged in");
    }
}
