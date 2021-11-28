package callOfDuty;

/**
 * Represents a Tank
 */
public class Tank extends Target {
    /**
     * name
     */
    public final String name = "tank";

    /**
     * constructor
     * @param base   of target
     */
    public Tank(Base base) {
        super(1,1, base);
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

    /**
     * @return the name of target
     */
    @Override
    public String getTargetName() {
        return this.name;
    }

    /**
     * Decide whether the tank is destroyed
     * @return true if it has been destroyed
     */
    @Override
    public boolean isDestroyed() {
        int row = this.getHit().length;
        int column = this.getHit()[0].length;
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (this.getHit()[i][j] < 2) return false;
            }
        }
        return true;
    }

    /**
     * @return a single-character String to use in the Base’s print method.
     */
    @Override
    public String toString() {
        if (this.isDestroyed()) return "X";
        else return "T";
    }



}
