package com.s63d.vehicle.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"USER_ID", "VEHICLE_ID"})
})
public class Ownership implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date startDate;
    private Date endDate;

    @ManyToOne
    private Vehicle vehicle;
    @ManyToOne(cascade = CascadeType.MERGE)
    private SimpleUser user;

    public Ownership() {}
    public Ownership(Date startDate, SimpleUser user, Vehicle vehicle) {
        this.startDate = startDate;
        this.user = user;
        this.vehicle = vehicle;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public SimpleUser getUser() {
        return user;
    }

    public void setUser(SimpleUser user) {
        this.user = user;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ownership)) return false;
        Ownership ownership = (Ownership) o;
        return getId() == ownership.getId() &&
                Objects.equals(getStartDate(), ownership.getStartDate()) &&
                Objects.equals(getEndDate(), ownership.getEndDate()) &&
                Objects.equals(getUser(), ownership.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStartDate(), getEndDate(), getUser());
    }
}
