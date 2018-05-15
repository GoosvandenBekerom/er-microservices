package com.s63d.vehicle.service;

import com.s63d.generic.DomainService;
import com.s63d.vehicle.domain.CarTracker;
import com.s63d.vehicle.domain.Vehicle;
import com.s63d.vehicle.repository.CarTrackerRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.NotFoundException;

@Stateless
public class CarTrackerService extends DomainService<CarTracker, Long, CarTrackerRepository> {
    private VehicleService vehicleService;
    @Inject
    public CarTrackerService(CarTrackerRepository repository, VehicleService vehicleService) {
        super(repository);
        this.vehicleService = vehicleService;
    }
    public CarTrackerService() { super(); }

    public void verifyCar(String carId, long trackerId) {
        Vehicle vehicle = vehicleService.getById(carId);
        CarTracker tracker = repo.getById(trackerId);
        if (vehicle == null) throw new NotFoundException("No vehicle found with id "+carId);
        if (tracker == null) throw new NotFoundException("No tracker found with id "+trackerId);
        if (vehicle.getCarTracker() != null) throw new BadRequestException("Vehicle already has a tracker");
        vehicle.setCarTracker(tracker);
        vehicleService.save(vehicle);
    }
}
