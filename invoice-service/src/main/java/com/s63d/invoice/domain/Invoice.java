package com.s63d.invoice.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Invoice {
    public Invoice() {
    }

    public Invoice(String client, Date createdAt, InvoiceStatus status, Double price, List<Trip> trips, Double distance) {
        this.client = client;
        this.createdAt = createdAt;
        this.status = status;
        this.price = price;
        this.trips = trips;
        this.distance = distance;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String client;
    private Date createdAt;
    private InvoiceStatus status;
    private Double price;
    private List<Trip> trips;
    private Double distance;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public InvoiceStatus getStatus() {
        return status;
    }

    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }
}
