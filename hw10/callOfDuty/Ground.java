package callOfDuty;

/**
 * Represents ground
 */
public class Ground extends Target {
    /**
     * name
     */
    static final String name = "ground";

    /**
     * constructor
     * @param base of target
     */
    public Ground(Base base) {
        super(1, 1, base);
        this.setHit(new int[1][1]);
    }

    /**
     * do not explode
     */
    @Override
    void explode() {

    }

    /**
     * @return name of target
     */
    @Override
    public String getTargetName() {
        return this.name;
    }

    /**
     * @return a single-character String to use in the Base’s print method.
     */
    @Override
    public String toString() {
        return "-";
    }
}
