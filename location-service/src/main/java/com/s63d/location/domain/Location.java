package com.s63d.location.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Location {
    public Location() {
    }

    public Location(Long tripId, Double lat, Double lon) {
        this.tripId = tripId;
        this.lat = lat;
        this.lon = lon;
    }

    @Id
    @GeneratedValue
    private Long id;
    private Long tripId;
    private Double lat;
    private Double lon;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
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
}
