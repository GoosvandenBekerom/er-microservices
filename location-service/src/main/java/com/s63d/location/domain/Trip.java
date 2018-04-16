package com.s63d.location.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

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
}
