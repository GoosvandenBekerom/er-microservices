package com.s63d.vehicle.repository;

import com.s63d.generic.Repository;
import com.s63d.vehicle.domain.Ownership;
import com.s63d.vehicle.domain.SimpleUser;
import com.s63d.vehicle.domain.Vehicle;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.Date;
import java.util.List;

@Stateless
public class OwnershipRepository extends Repository<Ownership, Long> {
    public OwnershipRepository() { super(Ownership.class); }

    public List<Ownership> getAllForUser(SimpleUser user) {
        TypedQuery<Ownership> q = em.createNamedQuery("user.getOwnerships", Ownership.class);
        q.setParameter("user", user);
        return q.getResultList();
    }

    public void suspend(Ownership ownership) {
        ownership.setEndDate(new Date());
    }

    public Ownership getLatestOfVehicle(Vehicle vehicle) {
        TypedQuery<Ownership> q = em.createNamedQuery("ownership.getByVehicle", Ownership.class);
        q.setParameter("vehicleId", vehicle.getId());
        return q.getSingleResult();
    }
}
