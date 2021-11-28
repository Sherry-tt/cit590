package callOfDuty;

/**
 * Represents Barrack
 */
public class Barrack extends Target {
    /**
     * name
     */
    static final String name = "barrack";

    /**
     * constructor
     * @param base  of target
     */
    public Barrack(Base base) {
        super(3, 1, base);
    }

    /**
     * do not explode
     */
    @Override
    void explode() {

    }

    /**
     * get the name of target
     */
    @Override
    public String getTargetName() {
        return this.name;
    }
}
