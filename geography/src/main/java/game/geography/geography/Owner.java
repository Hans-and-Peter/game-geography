package game.geography.geography;

public class Owner {
    private final OwnerName name;

    public Owner(OwnerName name) {
        this.name = name;
    }

    public void occupy(Land land) {
        land.owned(this);
    }

    public OwnerName named() {
        // TODO this is only here for the tests. State is given outside. Grrr.
        return name;
    }
}
