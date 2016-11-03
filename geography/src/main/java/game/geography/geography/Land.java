package game.geography.geography;

public class Land {
    private Owner owner;

    public Land(LandName name) {

    }

    public void owned(Owner owner) {
        this.owner = owner;
    }

    public Owner ownedBy() {
        return owner;
    }

    public String named() {
        return null;
    }
}
