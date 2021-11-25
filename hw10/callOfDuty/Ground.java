package callOfDuty;

public class Ground extends Target {
    static final String name = "ground";
    /**
     * constructor
     * @param base   of target
     */
    public Ground(Base base) {
        super(1, 1, base);
        this.setHit(new int[1][1]);
    }

    @Override
    void explode() {

    }

    @Override
    public String getTargetName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "-";
    }
}
