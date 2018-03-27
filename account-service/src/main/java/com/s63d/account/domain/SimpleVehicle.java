package com.s63d.account.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SimpleVehicle {
    @Id
    private String id;
    private String type;
    private String brand;
    private String color;

    public SimpleVehicle() {}
    public SimpleVehicle(String id, String type, String brand, String color) {
        this.id = id;
        this.type = type;
        this.brand = brand;
        this.color = color;
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
}
