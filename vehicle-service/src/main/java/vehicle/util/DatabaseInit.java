package vehicle.util;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class DatabaseInit {

    @PostConstruct
    void init() {

    }
}
