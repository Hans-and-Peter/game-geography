package game.geography.rest;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.Manifest;

public class ManifestVersion {

    private static final String MANIFEST_MF = "META-INF/MANIFEST.MF";

    private final String key;

    public ManifestVersion(String key) {
        this.key = key;
    }

    public String extract() {
        // see http://stackoverflow.com/a/1273196
        try {
            Enumeration<URL> resources = getClass().getClassLoader().getResources(MANIFEST_MF);
            while (resources.hasMoreElements()) {
                Manifest manifest = new Manifest(resources.nextElement().openStream());
                Attributes mainAttributes = manifest.getMainAttributes();
                String v = mainAttributes.getValue(key);
                if (v != null) {
                    return v;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "local";
    }

}
