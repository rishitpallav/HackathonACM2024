package com.iit.shareameal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Food {
    @Id
    private int id;
    private String name;
    private int calories;
    private int protien;
    private int fat;
    private int carbs;

    public Food() {
    }

    public Food(int id, String name, int calories, int protien, int fat, int carbs) {
        this.id = id;
        this.name = name;
        this.calories = calories;
        this.protien = protien;
        this.fat = fat;
        this.carbs = carbs;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCalories() {
        return calories;
    }

    public int getProtien() {
        return protien;
    }

    public int getFat() {
        return fat;
    }

    public int getCarbs() {
        return carbs;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public void setProtien(int protien) {
        this.protien = protien;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }
}
