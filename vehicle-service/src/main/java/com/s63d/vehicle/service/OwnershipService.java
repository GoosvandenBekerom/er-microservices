package com.s63d.vehicle.service;

import com.s63d.generic.DomainService;
import com.s63d.vehicle.domain.Ownership;
import com.s63d.vehicle.domain.SimpleUser;
import com.s63d.vehicle.domain.Vehicle;
import com.s63d.vehicle.repository.OwnershipRepository;
import com.s63d.vehicle.repository.VehicleRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

@Stateless
public class OwnershipService extends DomainService<Ownership, Long, OwnershipRepository> {
    private VehicleRepository vehicleRepo;
    @Inject
    public OwnershipService(OwnershipRepository repository, VehicleRepository vehicleRepo) {
        super(repository);
        this.vehicleRepo = vehicleRepo;
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
}
