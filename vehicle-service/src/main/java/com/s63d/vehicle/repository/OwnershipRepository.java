package com.s63d.vehicle.repository;

import com.s63d.generic.Repository;
import com.s63d.vehicle.domain.Ownership;
import com.s63d.vehicle.domain.SimpleUser;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
public class OwnershipRepository extends Repository<Ownership, Long> {
    public OwnershipRepository() { super(Ownership.class); }

    public List<Ownership> getAllForUser(SimpleUser user) {
        TypedQuery<Ownership> q = em.createNamedQuery("user.getOwnerships", Ownership.class);
        q.setParameter("user", user);
        return q.getResultList();
    }
}
