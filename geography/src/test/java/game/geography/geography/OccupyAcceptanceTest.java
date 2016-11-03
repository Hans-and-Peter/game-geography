package game.geography.geography;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class OccupyAcceptanceTest {

    private Map map = new Map();

    @Test
    public void should_own_land_when_occuping() {
        Owner newOwner = new Owner(new OwnerName("King Ragnar"));
        Land land = map.lookup(new LandName("Stormland"));

        newOwner.occupy(land);

        Land stormland = map.lookup(new LandName("Stormland"));
        assertThat(stormland.ownedBy().named(), is(new OwnerName("King Ragnar")));
    }
}