package game.geography.application;

import game.geography.rest.LandEndpoint;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class EndpointConfiguration extends ResourceConfig {

    public EndpointConfiguration() {
        register(LandEndpoint.class);
    }

}
