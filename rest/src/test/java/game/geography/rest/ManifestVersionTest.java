package game.geography.rest;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ManifestVersionTest {

    @Test
    public void should_return_local_for_non_existing_version() {
        String extract = new ManifestVersion("missing-key").extract();

        assertThat(extract, is("local"));
    }

    @Test
    public void should_return_version_from_manifest() {
        String extract = new ManifestVersion("Game-Service-Version").extract();

        assertThat(extract, is("1.0.0-0"));
    }
}
