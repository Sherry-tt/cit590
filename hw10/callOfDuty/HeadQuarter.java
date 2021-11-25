package callOfDuty;

public class HeadQuarter extends Target {
    static final String name = "headQuarter";
    /**
     * constructor
     * @param base   of target
     */
    //public HeadQuarter(int length, int width, Base base) {
//        super(length, width, base);
//    }
    public HeadQuarter(Base base) {
        super(6, 1, base);
    }

    @Override
    void explode() {

    }

    @Override
    public String getTargetName() {
        return this.name;
    }
}
