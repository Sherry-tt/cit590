package callOfDuty;

/**
 * Represents the RocketLauncher
 */
public class RocketLauncher extends Weapon {
    /**
     * constructor
     */
    public RocketLauncher() {
        super(20);
    }

    /**
     * get the type of weapon
     * @return "rocketLauncher"
     */
    @Override
    public String getWeaponType() {
        return "rocketLauncher";
    }

    /**
     * RocketLauncher will only shoot at one square
     * @param row
     * @param column
     * @param base
     */
    @Override
    public void shootAt(int row, int column, Base base) {
        base.shootAt(row, column);
        this.decrementShotLeft();
        base.incrementShotsCount();
    }
}
