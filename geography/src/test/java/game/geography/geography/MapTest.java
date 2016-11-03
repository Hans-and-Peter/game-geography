package game.geography.geography;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class MapTest {

    @Test
    public void should_find_land() {
        Map map = new Map();

        Land stormland = map.lookup(new LandName("Stormland"));

        assertThat(stormland.named(), is(new LandName("Stormland")));
    }
}
