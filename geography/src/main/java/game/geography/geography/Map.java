package game.geography.geography;

import java.util.HashMap;

public class Map implements LandOwnerChangeListener {

    private final HashMap<LandName, Land> landByName = new HashMap<>();

    public Map() {
        // in first version we have two lands
        save(new Land(new LandName("Stormland"), this));
        save(new Land(new LandName("Rainland"), this));
    }

    public Land lookup(LandName landName) {
        return findById(landName);
    }

    @Override
    public void landOwnerHasChanged(Land land) {
        save(land);
    }

    private Land findById(LandName landName) {
        return landByName.get(landName);
    }

    private void save(Land land) {
        landByName.put(land.named(), land);
    }

}
