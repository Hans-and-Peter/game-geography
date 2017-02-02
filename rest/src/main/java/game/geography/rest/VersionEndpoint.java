package game.geography.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Manifest;

@Path("version")
public class VersionEndpoint {

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public VersionResource show() {
        // see http://stackoverflow.com/a/1273196
        try {
            Enumeration<URL> resources = getClass().getClassLoader().getResources("META-INF/MANIFEST.MF");
            while (resources.hasMoreElements()) {
                Manifest manifest = new Manifest(resources.nextElement().openStream());
                String v = (String) manifest.getMainAttributes().get("Game-Service-Version");
                if (v != null) {
                    return new VersionResource(v);
                }
            }
        } catch (IOException e) {
            // handle
        }
        return new VersionResource("local");
    }

}
