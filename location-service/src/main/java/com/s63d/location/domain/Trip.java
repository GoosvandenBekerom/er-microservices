package com.s63d.location.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NamedQueries(
        @NamedQuery(name = "trip.getLocations", query = "SELECT l FROM Location l WHERE l.trip.id = :tripId")
)
public class Trip {

    public Trip() {

    }

    public Trip(Long trackerId) {
        this();
        this.trackerId = trackerId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @Override
    public String toString() {
        return "Trip{" +
                "id=" + id +
                ", trackerId=" + trackerId +
                '}';
    }
}
