package game.geography.geography;

import java.util.HashMap;

public class Map implements LandOwnerChangeListener {

    private final HashMap<LandName, Land> landByName = new HashMap<>();

    public Land lookup(LandName landName) {
        if (landByName.containsKey(landName)) {
            return landByName.get(landName);
        }

        Land land = new Land(landName, this);
        landByName.put(landName, land);

        return landByName.get(landName);
    }

    @Override
    public void landOwnerHasChanged(Land land) {
        landByName.put(land.named(), land);
    }

}
