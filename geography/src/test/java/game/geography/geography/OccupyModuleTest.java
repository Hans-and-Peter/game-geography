package game.geography.geography;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class OccupyModuleTest {
    // Module test is a system test of the whole Maven module.
    // It is an integration test.
    // For the outer loop this was our guiding test driving the
    // creation of other unit tests.
    // This is an acceptance tests of the business functionality.

    @Test
    public void should_own_land_when_occupying() {
        // Guiding test for the feature of happy path occupying a land.
        Owner newOwner = new Owner(new OwnerName("King Ragnar"));
        Map map = new Map(new HashMapLandRepository());
        Land land = map.lookup(new LandName("Stormland"));

        newOwner.occupy(land);

        Land stormland = map.lookup(new LandName("Stormland"));
        assertThat(stormland.ownedBy().named(), is(new OwnerName("King Ragnar")));
    }
}
