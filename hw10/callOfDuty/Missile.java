package src.callOfDuty;

/**
 * Represents a Missile
 */
public class Missile extends Weapon {
    /**
     * constructor
     */
    public Missile() {
        super(3);
    }

    /**
     * get the type of weapon
     * @return "missile"
     */
    @Override
    public String getWeaponType() {
        return "missile";
    }

    /**
     * missile attack a 3x3 area
     * @param row
     * @param column
     * @param base
     */
    @Override
    public void shootAt(int row, int column, Base base) {
        //base.shootAt(row, column);
        for(int i = row - 1; i < row + 2; i++) {
            if(i < 0 || i > 9) continue;
            for(int j = column -1; j < column + 2; j++) {
                if(j < 0 || j > 9) continue;
                base.shootAt(i,j);
            }
        }
        base.incrementShotsCount();
        this.decrementShotLeft();
    }
}
