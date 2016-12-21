package game.geography.geography;

public class Map implements LandChangeListener {

    private final LandRepository landRepository;

    public Map(LandRepository landRepository) {
        // in first version we have two lands
        // later, add its own class of LandGenerator or Seed
        this.landRepository = landRepository;
        this.landRepository.save(new Land(new LandName("Stormland"), this));
        this.landRepository.save(new Land(new LandName("Rainland"), this));
    }

    public Land lookup(LandName landName) {
        return landRepository.findById(landName);
    }

    @Override
    public void landHasChanged(Land land) {
        landRepository.save(land);
    }

}
