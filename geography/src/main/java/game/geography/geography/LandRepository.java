package game.geography.geography;

public interface LandRepository {

    Land findById(LandName landName);

    void save(Land land);
}
