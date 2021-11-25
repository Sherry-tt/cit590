package callOfDuty;

public class SentryTower extends Target {
    static final String name = "sentryTower";
    /**
     * constructor
     * @param base  of target
     */
    public SentryTower(Base base) {
        super(1, 1, base);
    }

    @Override
    void explode() {

    }

    @Override
    public String getTargetName() {
        return this.name;
    }
}
