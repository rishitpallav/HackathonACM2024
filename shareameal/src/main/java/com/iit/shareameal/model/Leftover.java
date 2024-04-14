package com.iit.shareameal.model;

import jakarta.persistence.Entity;

@Entity
public class Leftover {
    private int foodId;
    private int donorId;
    private int quantity;

    public Leftover() {
    }

    public Leftover(int foodId, int donorId, int quantity) {
        this.foodId = foodId;
        this.donorId = donorId;
        this.quantity = quantity;
    }

    public int getFoodId() {
        return foodId;
    }

    public int getDonorId() {
        return donorId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setFoodId(int foodId) {
        this.foodId = foodId;
    }

    public void setDonorId(int donorId) {
        this.donorId = donorId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
