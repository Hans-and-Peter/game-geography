package game.geography.geography;

public class OwnerName {
    private final String name;

    public OwnerName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        OwnerName that = (OwnerName) other;
        return this.name.equals(that.name);

    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return name;
    }
}
