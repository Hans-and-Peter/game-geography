package game.geography.geography;

import java.util.HashMap;

public class InMemoryLandRepository implements LandRepository {
    private final HashMap<LandName, Land> landByName = new HashMap<>();

    @Override
    public Land findById(LandName landName) {
        return landByName.get(landName);
    }

    @Override
    public void save(Land land) {
        landByName.put(land.named(), land);
    }
}
