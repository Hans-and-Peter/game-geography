package game.geography.geography;

public class Land {
    private final LandName name;
    private final LandOwnerChangeListener listener;
    private Owner owner;

    public Land(LandName name, LandOwnerChangeListener listener) {
        this.name = name;
        this.listener = listener;
    }

    public void owned(Owner newOwner) {
        owner = newOwner;
        landOwnerHasChanged();
    }

    private void landOwnerHasChanged() {
        listener.landOwnerHasChanged(this);
    }

    public Owner ownedBy() {
        return owner;
    }

    public LandName named() {
        return name;
    }
}
