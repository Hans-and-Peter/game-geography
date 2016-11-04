package game.geography.application;

import game.geography.rest.LandEndpoint;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        register(LandEndpoint.class);
    }

}
