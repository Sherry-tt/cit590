package callOfDuty;

/**
 * Abstract class Weapon represents a generic (or abstract) Weapon.
 */
public abstract class Weapon {
    /**
     * The number of shots left.
     */
    private int shotleft;

    /**
     * constructor
     * @param shotCount Initially, 20 for RocketLauncher and 3 for Missile.
     */
    public Weapon(int shotCount) {
        this.shotleft = shotCount;
    }

    /**
     * @return the left shot number
     */
    public int getShotLeft() {
        return this.shotleft;
    }

    /**
     * @return the type of weapon
     */
    public abstract String getWeaponType();

    /**
     * shoot at some place
     */
    public abstract void shootAt(int row, int column, Base base);

    /**
     * decrease one each time
     */
    public void decrementShotLeft() {
        this.shotleft -= 1;
    }
}
