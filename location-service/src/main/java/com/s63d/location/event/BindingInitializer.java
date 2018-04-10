package com.s63d.location.event;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;
import java.io.IOException;

@Startup
@Singleton
public class BindingInitializer {
    @Inject
    private RabbitBinder binder;

    @PostConstruct
    public void initialize() {

        try {
            binder.configuration()
                    .addHost("goosvandenbekerom.nl")
                    .setUsername("proftaak")
                    .setPassword("proftaak");
            binder.initialize();
        } catch (IOException e) {
            System.out.println("Oh no..");
            e.printStackTrace();
        }
    }
}
