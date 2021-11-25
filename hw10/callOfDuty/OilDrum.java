package callOfDuty;

public class OilDrum extends Target {
    static final String name = "oilDrum";
    /**
     * constructor
     * @param base   of target
     */

    public OilDrum(Base base) {
        super(1,1, base);
//        this.setHit(new int[1][1]);
    }

    /**
     * Define how the target will behave when it is destroyed.
     * hit a 5x5 area around it
     */
    @Override
    void explode() {
        int x = this.getCoordinate()[0];
        int y = this.getCoordinate()[1];
        for(int i = x-2; i <= x+2; i++) {
            if(i < 0) continue;
            if(i > 9) break;
            for(int j = y-2; j <= y+2; j++) {
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
