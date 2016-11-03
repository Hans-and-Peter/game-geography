package game.geography.geography;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LandTest {

    LandName landName = new LandName("Name of land is irrelevant");
    OwnerName ownerName = new OwnerName("Name of owner is irrelevant");

    @Test
    public void should_write_owner_back_to_map() {
        LandOwnerChangeListener listener = mock(LandOwnerChangeListener.class);
        Land land = new Land(landName, listener);

        land.owned(new Owner(ownerName));

        verify(listener).landOwnerHasChanged(landName, ownerName);
    }
}
