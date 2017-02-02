package game.geography.rest;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ManifestVersionTest {

    @Test
    public void shouldReturnLocalForMissingVersion() {
        ManifestVersion manifestVersion = new ManifestVersion("missing-key");
        String extract = manifestVersion.extract();
        assertThat(extract, is("local"));
    }

    @Test
    public void shouldExtractVersion() {
        ManifestVersion manifestVersion = new ManifestVersion("Test-Existing-Version");
        String extract = manifestVersion.extract();
        assertThat(extract, is("1.0.0-0"));
    }
}
