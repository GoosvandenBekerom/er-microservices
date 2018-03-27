package com.s63d.location.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Trip {


    @Id
    private Long id;

    private Long trackerId;

    public Long getId() {
        return id;
    }

    public Trip(Long trackerId) {
        this.trackerId = trackerId;
    }

    public Long getTrackerId() {
        return trackerId;
    }

    public void setTrackerId(Long trackerId) {
        this.trackerId = trackerId;
    }
}
