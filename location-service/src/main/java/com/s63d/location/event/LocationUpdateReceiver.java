package com.s63d.location.event;

import com.s63d.location.domain.Location;
import com.s63d.location.domain.Trip;
import com.s63d.location.repository.LocationRepository;
import com.s63d.location.repository.TripRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class LocationUpdateReceiver {

    @Inject
    private TripRepository tripRepo;

    @Inject
    private LocationRepository locationRepository;

    public void receiveEvent(@Observes LocationEvent locationEvent) {

        if(!tripRepo.exists(locationEvent.getTripId())) {
            System.out.println("Trip " + locationEvent.getTripId() + " does not exist");
            return;
        }
        Trip trip = tripRepo.getById(locationEvent.getTripId());

        Location location = locationRepository.save(locationEvent.toLocation(trip));

    }

}
