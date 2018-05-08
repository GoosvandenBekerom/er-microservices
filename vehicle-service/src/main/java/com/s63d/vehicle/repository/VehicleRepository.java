package com.s63d.vehicle.repository;

import com.s63d.vehicle.domain.Ownership;
import com.s63d.vehicle.domain.SimpleUser;
import com.s63d.vehicle.domain.Vehicle;
import com.s63d.generic.Repository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Stateless
public class VehicleRepository extends Repository<Vehicle, String> {
    @Inject
    private OwnershipRepository ownershipRepo;

    public VehicleRepository() { super(Vehicle.class); }

    public List<Vehicle> getAllVehicles(SimpleUser user) {
        List<Vehicle> vehicles = new ArrayList<>();
        for(Ownership o : ownershipRepo.getAllForUser(user)) {
            if (o.getEndDate() == null || o.getEndDate().before(new Date())) {
                vehicles.add(o.getVehicle());
            }
        }
        return vehicles;
    }
}
