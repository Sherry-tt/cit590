package callOfDuty;

public class Barrack extends Target {
    static final String name = "barrack";
    /**
     * constructor

     * @param base  of target
     */
//    public Barrack(int length, int width, Base base) {
//        super(length, width, base);
//    }

    public Barrack(Base base) {
        super(3, 1, base);
    }

    @Override
    void explode() {

    }

    @Override
    public String getTargetName() {
        return this.name;
    }
}
