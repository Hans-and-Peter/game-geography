package game.geography.rest;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

public class ManifestVersion {

    private String name;

    public ManifestVersion(String name) {
        this.name = name;
    }

    public String extract() {
        // see http://stackoverflow.com/a/1273196
        try {
            Enumeration<URL> resources = getClass().getClassLoader().getResources("META-INF/MANIFEST.MF");
            while (resources.hasMoreElements()) {
                Manifest manifest = new Manifest(resources.nextElement().openStream());
                Attributes mainAttributes = manifest.getMainAttributes();
                String v = mainAttributes.getValue(name);
                if (v != null) {
                    return v;
                }
            }
        } catch (IOException e) {
            // handle
        }
        return "local";
    }

}
