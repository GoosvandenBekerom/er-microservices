package com.s63d.location.domain;

import javax.persistence.*;

@Entity
public class Location {
    public Location() {
    }


    public Location(Trip trip, Double lat, Double lon) {
        this.lat = lat;
        this.trip = trip;
        this.lon = lon;
    }

    @Id
    @GeneratedValue
    private Long id;
    private Double lat;
    private Double lon;
    @ManyToOne
    private Trip trip;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
