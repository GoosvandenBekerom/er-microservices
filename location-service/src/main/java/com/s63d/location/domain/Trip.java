package com.s63d.location.domain;

import javax.persistence.*;
import java.util.List;

@Entity
public class Trip {

    public Trip() {
    }

    public Trip(Long trackerId) {
        this.trackerId = trackerId;
    }

    @Id
    @GeneratedValue
    private Long id;

    private Long trackerId;

    public Long getId() {
        return id;
    }


    public Long getTrackerId() {
        return trackerId;
    }

    public void setTrackerId(Long trackerId) {
        this.trackerId = trackerId;
    }

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trip")
    private List<Location> locations;

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }
}
