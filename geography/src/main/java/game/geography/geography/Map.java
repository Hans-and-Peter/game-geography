package game.geography.geography;

public class Map {
    public Land lookup(LandName landName) {
        return new Land(landName);
    }
}
