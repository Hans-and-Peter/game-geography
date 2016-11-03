package game.geography.geography;

public class Land {
    private final LandName name;
    private Owner owner;

    public Land(LandName name) {
        this.name = name;
    }

    public void owned(Owner owner) {
        this.owner = owner;
    }

    public Owner ownedBy() {
        return owner;
    }

    public LandName named() {
        // TODO this is only here for the tests. State is given outside. Grrr.
        return name;
    }
}
