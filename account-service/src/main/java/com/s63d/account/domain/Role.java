package com.s63d.account.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Role implements Serializable {
    @Id
    private String name;
    private String description;

    @OneToMany
    private List<Permission> permissions;

    public Role() {}
    public Role(String name, String description) {
        this.name = name;
        this.description = description;
        this.permissions = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Permission> getPermissions() {
        if (permissions == null) {
            permissions = new ArrayList<>();
        }
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        Role role = (Role) o;
        return Objects.equals(getName(), role.getName()) &&
                Objects.equals(getDescription(), role.getDescription()) &&
                Objects.equals(getPermissions(), role.getPermissions());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getDescription(), getPermissions());
    }
}
