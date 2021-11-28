package callOfDuty;

/**
 * Represents HeadQuarter
 */
public class HeadQuarter extends Target {
    /**
     * name
     */
    static final String name = "headQuarter";
    /**
     * constructor
     * @param base   of target
     */
    public HeadQuarter(Base base) {
        super(6, 1, base);
    }

    /**
     * do not explode
     */
    @Override
    void explode() {

    }

    /**
     * @return the name
     */
    @Override
    public String getTargetName() {
        return this.name;
    }
}
