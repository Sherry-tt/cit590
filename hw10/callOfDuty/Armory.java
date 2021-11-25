package callOfDuty;

public class Armory extends Target {

    static final String name = "armory";
    /**
     * constructor
     * @param base of target
     */
    public Armory(Base base) {
        super(3, 2, base);
    }

    /**
     * Define how the target will behave when it is destroyed.
     * hit a 6x7(7x6) area around it
     */

    @Override
    void explode() {
        int x = this.getCoordinate()[0];
        int y = this.getCoordinate()[1];
        int length = this.getLength();
        int width = this.getWidth();
        for(int i = x-2; i < x+width+2; i++) {
            if(i < 0) continue;
            if(i > 9) break;
            for(int j = y-2; j < y+length+2; j++) {
                if(j < 0) continue;
                if(j > 9) break;
                this.getBase().shootAt(i,j);
            }
        }

    }

    @Override
    public String getTargetName() {
        return this.name;
    }
}
