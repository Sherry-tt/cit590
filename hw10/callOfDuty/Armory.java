package callOfDuty;

/**
 * Represents Armory
 */
public class Armory extends Target {

    /**
     * name
     */
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
        if (this.getHorizontal() == true) {
            for(int i = x-2; i <= x+4; i++) {
                if(i < 0) continue;
                if(i > 9) break;
                for(int j = y-2; j <= y + 3; j++) {
                    if(j < 0) continue;
                    if(j > 9) break;
                    this.getBase().shootAt(i,j);
                }
            }
        } else {
            for(int i = x-2; i <= x + 3; i++) {
                if(i < 0) continue;
                if(i > 9) break;
                for(int j = y-2; j <= y+4; j++) {
                    if(j < 0) continue;
                    if(j > 9) break;
                    this.getBase().shootAt(i,j);
                }
            }
        }
    }

    /**
     * get the name of target
     */
    @Override
    public String getTargetName() {
        return this.name;
    }
}
