package com.s63d.account.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Entity
public class Ownership implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Date startDate;
    private Date endDate;
    @ManyToOne
    private User user;
    private String carId;

    public Ownership() {}
    public Ownership(Date startDate, Date endDate, User user, String carId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.user = user;
        this.carId = carId;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCarId() {
        return carId;
    }

    public void setCarId(String carId) {
        this.carId = carId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ownership)) return false;
        Ownership ownership = (Ownership) o;
        return getId() == ownership.getId() &&
                Objects.equals(getStartDate(), ownership.getStartDate()) &&
                Objects.equals(getEndDate(), ownership.getEndDate()) &&
                Objects.equals(getUser(), ownership.getUser()) &&
                Objects.equals(getCarId(), ownership.getCarId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getStartDate(), getEndDate(), getUser(), getCarId());
    }
}
