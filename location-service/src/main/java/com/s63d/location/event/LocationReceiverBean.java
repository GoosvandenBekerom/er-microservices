package com.s63d.location.event;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

@ApplicationScoped
public class LocationReceiverBean {

    public void receiveEvent(@Observes LocationEvent locationEvent) {
        System.out.println("Received event!");
        System.out.println(locationEvent.toString());

    }

}
