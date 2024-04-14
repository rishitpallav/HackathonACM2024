package com.iit.shareameal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.iit.shareameal.model.Donor;
import com.iit.shareameal.model.Shelter;
import com.iit.shareameal.repository.DonorRepository;
import com.iit.shareameal.repository.ShelterRepository;

@Controller
public class SessionController {

    @Autowired
    private Donor loginDonor = new Donor();
    @Autowired
    private Shelter loginShelter = new Shelter();
    @Autowired
    private boolean isLoginDonor = false;

    @Autowired
    private DonorRepository donorRepository;

    @Autowired
    private ShelterRepository shelterRepository;

    @GetMapping("/login/donor")
    public ResponseEntity<?> loginDonor(@RequestParam String email, @RequestParam String password) {
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
    public ResponseEntity<?> registerDonor(@RequestParam Donor donor) {
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
}
