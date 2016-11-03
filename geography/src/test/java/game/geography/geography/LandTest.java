package game.geography.geography;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class LandTest {

    LandName landName = new LandName("Name of land is irrelevant");
    OwnerName ownerName = new OwnerName("Name of owner is irrelevant");

    @Test
    public void should_write_owner_back_to_map() {
        LandOwnerChangeListener map = mock(LandOwnerChangeListener.class);
        Land land = new Land(landName);

        land.owned(new Owner(ownerName));

        verify(map).landOwnerHasChanged(new LandOwnerHasChanged(landName, ownerName));
    }
}
