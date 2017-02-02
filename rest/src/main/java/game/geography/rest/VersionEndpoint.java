package game.geography.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("version")
public class VersionEndpoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public VersionResource show() {
        return new VersionResource(new ManifestVersion("Game-Service-Version").extract());
    }

}
