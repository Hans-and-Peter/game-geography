package game.geography.geography;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

public class MapTest {

    private Map map = new Map(new HashMapLandRepository());
    private final LandName landName = new LandName("Stormland");

    @Test
    public void should_find_land() {
        Land stormland = map.lookup(landName);

        assertThat(stormland.named(), is(landName));
    }

    // TODO DDD: we would like to write test that does not expose inner state like name
    @Test @Ignore("we would like to write test that does not expose inner state like name") 
    public void should_return_land_consistently() {
        Land stormland1 = map.lookup(landName);
        Land stormland2 = map.lookup(landName);
        Land rainland = map.lookup(new LandName("Rainland"));

        assertThat(stormland1, is(stormland2));
        assertThat(stormland1, is(not(rainland)));
    }

    @Test
    public void should_persist_changes_in_land_ownership() {
        Land land = new Land(landName, map);
        Owner owner = new Owner(new OwnerName("Chief Maly"));

        owner.occupy(land);

        Land stormland = map.lookup(landName);
        assertThat(stormland.ownedBy().named(), is(new OwnerName("Chief Maly")));
    }
}
