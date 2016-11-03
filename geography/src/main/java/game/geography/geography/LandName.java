package game.geography.geography;

public class LandName {
    private final String name;

    public LandName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object other) {
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        LandName that = (LandName) other;
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
