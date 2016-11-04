package game.geography.geography;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LandTest {

    private final LandName landName = new LandName("Name of land is irrelevant");
    private final OwnerName ownerName = new OwnerName("Name of owner is irrelevant");

    @Test
    public void should_write_owner_back_to_map() {
        LandChangeListener listener = mock(LandChangeListener.class);
        Land land = new Land(landName, listener);

        land.owned(new Owner(ownerName));

        verify(listener).landHasChanged(land);
    }
}
