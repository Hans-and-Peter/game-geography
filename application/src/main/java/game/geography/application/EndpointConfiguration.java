package game.geography.application;

import game.geography.rest.LandEndpoint;
import game.geography.rest.VersionEndpoint;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

@Component
public class EndpointConfiguration extends ResourceConfig {

    public EndpointConfiguration() {
        register(LandEndpoint.class);
        register(VersionEndpoint.class);
    }

}
