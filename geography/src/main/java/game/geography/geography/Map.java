package game.geography.geography;

public class Map implements LandOwnerChangeListener {

    private final LandRepository landRepository = new InMemoryLandRepository();

    public Map() {
        // in first version we have two lands
        landRepository.save(new Land(new LandName("Stormland"), this));
        landRepository.save(new Land(new LandName("Rainland"), this));
        // TODO later, maybe its own class of LandGenerator or Seed
    }

    public Land lookup(LandName landName) {
        return landRepository.findById(landName);
    }

    @Override
    public void landOwnerHasChanged(Land land) {
        landRepository.save(land);
    }

}
