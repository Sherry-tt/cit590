package callOfDuty;

/**
 * Represents a SentryTower
 */
public class SentryTower extends Target {
    static final String name = "sentryTower";
    /**
     * constructor
     * @param base  of target
     */
    public SentryTower(Base base) {
        super(1, 1, base);
    }

    /**
     * do not explode
     */
    @Override
    void explode() {

    }

    /**
     * @return the target name
     */
    @Override
    public String getTargetName() {
        return this.name;
    }
}
