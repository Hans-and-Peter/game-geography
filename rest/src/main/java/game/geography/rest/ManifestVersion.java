package game.geography.rest;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
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
                URL url = resources.nextElement();

                try (InputStream in = url.openStream()) {
                    Manifest manifest = new Manifest(in);
                    String value = manifest.getMainAttributes().getValue(key);
                    if (value != null) {
                        return value;
                    }
                }

            }
        } catch (IOException e) {
            return "no-version";
        }

        return "no-version";
    }

}
