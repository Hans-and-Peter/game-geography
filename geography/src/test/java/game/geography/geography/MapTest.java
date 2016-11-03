package game.geography.geography;

import org.junit.Ignore;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;

public class MapTest {

    @Test
    public void should_find_land() {
        Map map = new Map();

        Land stormland = map.lookup(new LandName("Stormland"));

        assertThat(stormland.named(), is(new LandName("Stormland")));
    }

    @Test @Ignore("we would like to write test that does not expose inner state like name")
    public void should_return_land_consistently() {
        Map map = new Map();

        Land stormland1 = map.lookup(new LandName("Stormland"));
        Land stormland2 = map.lookup(new LandName("Stormland"));
        Land rainland = map.lookup(new LandName("Rainland"));

        assertThat(stormland1, is(stormland2));
        assertThat(stormland1, is(not(rainland)));
    }
}
