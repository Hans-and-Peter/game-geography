package game.geography.geography;

public class Land {
    private final LandName name;
    private final LandChangeListener listener;
    private Owner owner;

    public Land(LandName name, LandChangeListener listener) {
        this.name = name;
        this.listener = listener;
    }

    // package class is only for core domain, not from outside!
    // Only called from Owner.
    void owned(Owner newOwner) {
        owner = newOwner;
        landOwnerHasChanged();
    }

    private void landOwnerHasChanged() {
        listener.landHasChanged(this);
    }

    public Owner ownedBy() {
        return owner;
    }

    public LandName named() {
        return name;
    }
}
