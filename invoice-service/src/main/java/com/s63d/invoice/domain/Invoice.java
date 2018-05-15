package com.s63d.invoice.domain;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Invoice {
    public Invoice() {
    }

    public Invoice(String client, Date createdAt, InvoiceStatus status, Double price, List<SimpleTrip> trips, Double distance) {
        this.client = client;
        this.createdAt = createdAt;
        this.status = status;
        this.price = price;
        this.trips = trips;
    }

    public Invoice(String client, List<SimpleTrip> trips) {
        this.client = client;
        this.trips = trips;
        this.status = InvoiceStatus.OPEN;

        this.createdAt = new Date();
        this.price = this.getDistance();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String client;
    private Date createdAt;
    private InvoiceStatus status;
    private Double price;

    @OneToMany
    private List<SimpleTrip> trips;

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

    public List<SimpleTrip> getTrips() {
        return trips;
    }

    public void setTrips(List<SimpleTrip> trips) {
        this.trips = trips;
    }

    public Double getDistance() {
        return trips.stream().mapToDouble(SimpleTrip::getDistance).sum();
    }

}
