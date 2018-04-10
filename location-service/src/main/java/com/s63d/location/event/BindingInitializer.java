package com.s63d.location.event;

import org.slf4j.LoggerFactory;

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
        binder.configuration()
                    .addHost("goosvandenbekerom.nl")
                    .setUsername("proftaak")
                    .setPassword("proftaak");
        try {
            binder.initialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
