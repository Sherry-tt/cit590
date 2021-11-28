package callOfDuty;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class WeaponTest {

    Base base;
    Missile mis;
    RocketLauncher rl;

    @BeforeEach
    void setUp() throws Exception {

        base = new Base();


        mis = new Missile();
        rl = new RocketLauncher();
    }

    @Test
    void testWeapon() {
        assertEquals(3, mis.getShotLeft());

        // TODO: add more cases
        assertEquals(20, rl.getShotLeft());

        Missile missile = new Missile();
        assertEquals(3, missile.getShotLeft());
    }

    @Test
    void testGetWeaponType() {
        assertEquals("missile", mis.getWeaponType().toLowerCase());

        // TODO: add more cases
        assertEquals("rocketlauncher", rl.getWeaponType().toLowerCase());

        Missile missile = new Missile();
        assertEquals("missile", missile.getWeaponType().toLowerCase());
    }


    @Test
    void testGetShotLeft() {
        mis.shootAt(0, 0, this.base);
        assertEquals(2, mis.getShotLeft());

        // TODO: add more cases
        rl.shootAt(2,1,this.base);
        assertEquals(19, rl.getShotLeft());

        mis.shootAt(0, 4, this.base);
        assertEquals(1, mis.getShotLeft());

        rl.shootAt(0,6,this.base);
        rl.shootAt(2,4,this.base);
        assertEquals(17, rl.getShotLeft());
    }

    @Test
    void testDecrementShotleft() {
        mis.decrementShotLeft();
        assertEquals(2, mis.getShotLeft());

        // TODO: add more cases
        mis.decrementShotLeft();
        mis.decrementShotLeft();
        assertEquals(0, mis.getShotLeft());

        rl.decrementShotLeft();
        rl.decrementShotLeft();
        assertEquals(18, rl.getShotLeft());

        rl.decrementShotLeft();
        assertEquals(17, rl.getShotLeft());
    }

    @Test
    void testShootAt() {
        mis.shootAt(0, 0, this.base);
        assertTrue(base.getTargetsArray()[0][0].isHitAt(0, 0));
        assertEquals(1, base.getShotsCount());
        // TODO: add more cases

        rl.shootAt(1, 3, this.base);
        assertTrue(base.getTargetsArray()[1][3].isHitAt(1, 3));
        assertEquals(2, base.getShotsCount());

        rl.shootAt(0, 6, this.base);
        assertTrue(base.getTargetsArray()[0][6].isHitAt(0, 6));
        assertEquals(3, base.getShotsCount());
    }
}
