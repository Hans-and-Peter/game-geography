package game.geography.geography;

public class Map implements LandOwnerChangeListener {

    public Land lookup(LandName landName) {
        return new Land(landName, this);
    }

    @Override
    public void landOwnerHasChanged(LandName land, OwnerName owner) {

    }

}
