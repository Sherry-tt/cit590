package callOfDuty;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BaseTest {

    Base base;
    Armory armory;
    Barrack barrack;
    SentryTower st;
    Tank tank;
    OilDrum od;

    @BeforeEach
    void setUp() throws Exception {

        base = new Base();

        armory = new Armory(base);
        base.placeTargetAt(armory, 0, 0, true);

        barrack = new Barrack(base);
        base.placeTargetAt(barrack, 0, 4, true);

        st = new SentryTower(base);
        base.placeTargetAt(st, 2, 4, true);

        tank = new Tank(base);
        base.placeTargetAt(tank, 1, 3, true);

        od = new OilDrum(base);
        base.placeTargetAt(od, 2, 1, true);
    }

    @Test
    void testBase() {
        assertEquals(10, base.getTargetsArray().length);

        // TODO: add more cases
        assertEquals(10, base.getTargetsArray()[0].length);
        assertEquals("armory", base.getTargetsArray()[0][0].getTargetName().toLowerCase());
        assertEquals("ground", base.getTargetsArray()[3][3].getTargetName().toLowerCase());
    }

    @Test
    void testPlaceAllTargetRandomly() {
        this.base = new Base();
        base.placeAllTargetRandomly();
        List<Target> list = new ArrayList<Target>();
        int headQuarterCount = 0;
        int armoryCount = 0;
        int barracksCount = 0;
        int sentryCount = 0;
        int tanksCount = 0;
        int odCount = 0;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (base.getTargetsArray()[i][j].getTargetName() != "ground") {
                    if (!list.contains(base.getTargetsArray()[i][j])) {
                        list.add(base.getTargetsArray()[i][j]);
                        switch (base.getTargetsArray()[i][j].getTargetName().toLowerCase()) {
                        case "armory": {
                            armoryCount++;
                            break;
                        }
                        case "headquarter": {
                            headQuarterCount++;
                            break;
                        }
                        case "barrack": {
                            barracksCount++;
                            break;
                        }
                        case "sentrytower": {
                            sentryCount++;
                            break;
                        }
                        case "tank": {
                            tanksCount++;
                            break;
                        }
                        case "oildrum": {
                            odCount++;
                            break;
                        }
                        }
                    }
                }
            }
        }
        assertEquals(list.size(), 18);

        assertEquals(1, headQuarterCount);
        assertEquals(2, armoryCount);
        assertEquals(3, barracksCount);
        assertEquals(4, sentryCount);
        assertEquals(4, tanksCount);
        assertEquals(4, odCount);
    }

    @Test
    void testOkToPlaceTargetAt() {
        assertFalse(this.base.okToPlaceTargetAt(new Armory(this.base), 1, 7, false));
        assertFalse(this.base.okToPlaceTargetAt(new Armory(this.base), 1, 8, true));
        assertTrue(this.base.okToPlaceTargetAt(new Armory(this.base), 1, 8, false));

        // TODO: add more cases
        assertFalse(this.base.okToPlaceTargetAt(new Barrack(this.base), 5, 8, true));
        assertFalse(this.base.okToPlaceTargetAt(new Barrack(this.base), 1, 6, true));
        assertTrue(this.base.okToPlaceTargetAt(new OilDrum(this.base), 2, 5, true));
    }



    @Test
    void testPlaceTargetAt() {
        Target armory = new Armory(base);
        this.base.placeTargetAt(armory, 5, 5, false);
        assertEquals(5, armory.getCoordinate()[0]);
        assertEquals(5, armory.getCoordinate()[1]);
        assertEquals(3, armory.getHit().length);
        assertEquals(2, armory.getHit()[0].length);

     // TODO: add more cases
        Target barrack = new Barrack(base);
        this.base.placeTargetAt(barrack, 5, 0, true);
        assertEquals(5, barrack.getCoordinate()[0]);
        assertEquals(0, barrack.getCoordinate()[1]);
        assertEquals(1, barrack.getHit().length);
        assertEquals(3, barrack.getHit()[0].length);

        Target barrackTwo = new Barrack(base);
        this.base.placeTargetAt(barrackTwo, 7, 3, false);
        assertEquals(7, barrackTwo.getCoordinate()[0]);
        assertEquals(3, barrackTwo.getCoordinate()[1]);
        assertEquals(3, barrackTwo.getHit().length);
        assertEquals(1, barrackTwo.getHit()[0].length);

        Target tank = new Tank(base);
        this.base.placeTargetAt(tank, 9, 8, false);
        assertEquals(9, tank.getCoordinate()[0]);
        assertEquals(8, tank.getCoordinate()[1]);
        assertEquals(1, tank.getHit().length);
        assertEquals(1, tank.getHit()[0].length);

    }


    @Test
    void testIsOccupied() {

        Armory arm = new Armory(base);
        this.base.placeTargetAt(arm, 0, 0, true);
        assertTrue(base.isOccupied(0, 0));

        // TODO: add more cases
        assertTrue(base.isOccupied(0, 4));

        assertFalse(base.isOccupied(7, 5));

        Target barrackTwo = new Barrack(base);
        this.base.placeTargetAt(barrackTwo, 7, 3, false);
        assertTrue(base.isOccupied(9, 3));
    }

    @Test
    void testShootAt() {

        Armory arm = new Armory(base);
        this.base.placeTargetAt(arm, 5, 5, true);

        base.shootAt(5, 5);
        assertTrue(arm.isHitAt(5, 5));

        // TODO: add more cases
        base.shootAt(0, 0);
        assertFalse(armory.isHitAt(0, 1));

        Target barrackTwo = new Barrack(base);
        this.base.placeTargetAt(barrackTwo, 7, 3, false);
        base.shootAt(8, 3);
        assertTrue(barrackTwo.isHitAt(8, 3));

        Target tank = new Tank(base);
        this.base.placeTargetAt(tank, 9, 8, false);
        base.shootAt(9, 8);
        assertTrue(tank.isHitAt(9, 8));
    }

    @Test
    void testIsGameOver() {

        assertFalse(base.isGameOver(new RocketLauncher(), new Missile()));

        // TODO: add more cases
        Weapon weaponRocket = new RocketLauncher();
        Weapon weaponMissile = new Missile();

        // use weaponMissile shot 3 times
        for(int i = 0; i < 3; i++) {
            weaponMissile.shootAt(1,2, base);
        }
        assertEquals(0, weaponMissile.getShotLeft());
        assertFalse(base.isGameOver(weaponRocket, weaponMissile));

        // use weaponRocket shot 3 times
        for(int i = 0; i<20; i++)  {
            weaponRocket.shootAt(2,2, base);
        }
        assertEquals(0, weaponRocket.getShotLeft());
        assertTrue(base.isGameOver(weaponRocket, weaponMissile));

        // new weaponRocket
        Weapon weaponRocketTwo = new RocketLauncher();
        assertFalse(base.isGameOver(weaponRocketTwo, weaponMissile));
    }

    @Test
    void testWin() {
        assertFalse(this.base.win());

        // TODO: add more cases
        base.shootAt(8, 3);
        assertFalse(this.base.win());

        base.shootAt(0, 0);
        assertFalse(this.base.win());

        base.shootAt(9, 9);
        assertFalse(this.base.win());
    }

    @Test
    void testIncrementAndSetShotsCount() {

        assertEquals(0, base.getShotsCount());
        base.incrementShotsCount();
        assertEquals(1, base.getShotsCount());

        // TODO: add more cases
        // increment two times
        base.incrementShotsCount();
        base.incrementShotsCount();
        assertEquals(3, base.getShotsCount());

        // increment another two times
        base.incrementShotsCount();
        base.incrementShotsCount();
        assertEquals(5, base.getShotsCount());

        // increment another one time
        base.incrementShotsCount();
        assertEquals(6, base.getShotsCount());
    }

    @Test
    void testSetAndGetDestroyedTargetCount() {
        base.setDestroyedTargetCount(10);
        assertEquals(10, base.getDestroyedTargetCount());

        base.setDestroyedTargetCount(6);
        assertEquals(6, base.getDestroyedTargetCount());

        base.setDestroyedTargetCount(0);
        assertEquals(0, base.getDestroyedTargetCount());

        base.setDestroyedTargetCount(2);
        assertEquals(2, base.getDestroyedTargetCount());
    }

    @Test
    void testGetTargetsArray() {
        assertEquals(10, base.getTargetsArray().length);

        assertEquals(base.getTargetsArray()[0][1], base.getTargetsArray()[0][0]);

        // add a new barrack
        Target barrackTwo = new Barrack(base);
        this.base.placeTargetAt(barrackTwo, 7, 3, false);
        assertEquals(base.getTargetsArray()[7][3], base.getTargetsArray()[8][3]);

        assertEquals("barrack", base.getTargetsArray()[7][3].getTargetName().toLowerCase());
    }
}
