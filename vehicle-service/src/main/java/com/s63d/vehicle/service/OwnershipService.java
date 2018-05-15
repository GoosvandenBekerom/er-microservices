package com.s63d.vehicle.service;

import com.s63d.generic.DomainService;
import com.s63d.vehicle.domain.Ownership;
import com.s63d.vehicle.domain.SimpleUser;
import com.s63d.vehicle.domain.Vehicle;
import com.s63d.vehicle.repository.OwnershipRepository;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.NotAuthorizedException;
import javax.ws.rs.NotFoundException;
import java.util.Date;
import java.util.List;

@Stateless
public class OwnershipService extends DomainService<Ownership, Long, OwnershipRepository> {
    @Inject
    public OwnershipService(OwnershipRepository repository) {
        super(repository);
    }
    public OwnershipService() { super(); }

    public void create(SimpleUser owner, Vehicle vehicle) {
        Ownership ownership = new Ownership(new Date(), owner, vehicle);
        owner.getOwnerships().add(ownership);
        repo.save(ownership);
    }

    public List<Ownership> getAllForUser(SimpleUser user) {
        return repo.getAllForUser(user);
    }

    public void suspend(Vehicle vehicle, SimpleUser user) {
        Ownership ownership = getLatestOfVehicle(vehicle);
        if (ownership == null) throw new NotFoundException("No current ownership found for this vehicle");
        if (ownership.getUser() != user) throw new NotAuthorizedException("You can't suspend someone else's car");
        repo.suspend(ownership);
    }

    private Ownership getLatestOfVehicle(Vehicle vehicle) {
        return repo.getLatestOfVehicle(vehicle);
    }
}
