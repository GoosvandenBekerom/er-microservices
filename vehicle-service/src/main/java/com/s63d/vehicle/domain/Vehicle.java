package com.s63d.vehicle.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Vehicle {
    @Id
    private String id;
    private String type;
    private String brand;
    private String color;
    private int weight;
    private char rate;

    public Vehicle() {}
    public Vehicle(String id, String type, String brand, String color, int weight, char rate) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.color = color;
        this.weight = weight;
        this.rate = rate;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }
    public String getColor() {
        return color;
    }
    public void setColor(String color) {
        this.color = color;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public char getRate() {
        return rate;
    }
    public void setRate(char rate) {
        this.rate = rate;
    }
}
