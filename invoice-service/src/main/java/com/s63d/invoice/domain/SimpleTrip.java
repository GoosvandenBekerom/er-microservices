package com.s63d.invoice.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SimpleTrip {
    public SimpleTrip() {}

    public SimpleTrip(Long tripId, Double distance, String polyline) {
        this.tripId = tripId;
        this.distance = distance;
        this.polyline = polyline;
    }

    @Id
    private Long tripId;
    private Double distance;
    private String polyline;

    public Long getTripId() {
        return tripId;
    }

    public void setTripId(Long tripId) {
        this.tripId = tripId;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public String getPolyline() {
        return polyline;
    }

    public void setPolyline(String polyline) {
        this.polyline = polyline;
    }
}
