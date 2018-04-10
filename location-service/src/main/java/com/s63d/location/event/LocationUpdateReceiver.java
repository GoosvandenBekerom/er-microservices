package com.s63d.location.event;

import com.s63d.location.repository.LocationRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class LocationUpdateReceiver {

    @Inject
    private LocationRepository repo;

    public void receiveEvent(@Observes LocationEvent locationEvent) {
        repo.save(locationEvent.toLocation());
    }

}
