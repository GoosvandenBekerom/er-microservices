package com.s63d.vehicle.domain;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "user.getOwnerships", query = "SELECT o FROM Ownership o WHERE o.user = :user"),
})
public class SimpleUser implements Serializable{
    @Id
    private long id;

    private String email;
    private String firstName;
    private String lastName;
    private String address;
    private String postal;
    private String city;

    @OneToMany(mappedBy = "user")
    @JsonbTransient
    private List<Ownership> ownerships;

    public SimpleUser() {
        this.ownerships = new ArrayList<>();
    }

    public SimpleUser(long id, String email, String firstName, String lastName, String address, String postal, String city) {
        this();
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.postal = postal;
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostal() {
        return postal;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public List<Ownership> getOwnerships() {
        return ownerships;
    }
    public void setOwnerships(List<Ownership> ownerships) {
        this.ownerships = ownerships;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SimpleUser)) return false;
        SimpleUser user = (SimpleUser) o;
        return Objects.equals(getFirstName(), user.getFirstName()) &&
                Objects.equals(getLastName(), user.getLastName()) &&
                Objects.equals(getEmail(), user.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastName(), getEmail());
    }
}
