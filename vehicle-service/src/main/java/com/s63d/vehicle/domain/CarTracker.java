package com.s63d.vehicle.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CarTracker {
    @Id
    private long id;

    public CarTracker() {}
    public CarTracker(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
}
