package callOfDuty;

import java.util.Arrays;
import java.util.Random;

public class Base {
    /**
     * Keeps a reference to the location of every Target in the game.
     * Every location in this array points to a Target,
     * specifically, an instance of a subclass of Target.
     */
    private Target[][] targets;

    /**
     * The total number of shots fired by the user.
     */
    private int shotsCount;

    /**
     * The number of targets destroyed.
     */
    private int destroyedTargetCount;

    /**
     * Creates an 10x10 ”empty” Base
     */
    public Base() {
        this.shotsCount = 0;
        this.destroyedTargetCount = 0;
        // fills the Targets array with Ground objects
        // this.targets = new Ground[10][10];
        this.targets = new Target[10][10];
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                Ground ground = new Ground(this);
                ground.setCoordinate(new int[] {i,j});
                this.targets[i][j] =ground;
            }
        }
    }

    /**
     * Create and place all Targets randomly on the Base
     */
    public void placeAllTargetRandomly() {
        Random rand = new Random();
        int count = 1;

        while (count <= 18) {
            int row = rand.nextInt(10);
            int column = rand.nextInt(10);
            int trueOrFalse = rand.nextInt(2);
            boolean horizontal = trueOrFalse == 0 ? true : false;
            // headquarter place
            if (count == 1) {
                HeadQuarter headQuarter = new HeadQuarter(this);
                if (okToPlaceTargetAt(headQuarter, row, column, horizontal)) {
                    placeTargetAt(headQuarter, row, column, horizontal);
                    count++;
//
                }
            } else if(count == 2 || count == 3) {
                // Armory
                Armory armory = new Armory(this);
                if (okToPlaceTargetAt(armory, row, column, horizontal)) {
                    placeTargetAt(armory, row, column, horizontal);
                    count++;
//                    setTargetVariable(armory, row, column, 2, 3, horizontal);
                }
            } else if(count == 4 || count == 5 || count == 6 ) {
                Barrack barrack = new Barrack(this);
                if (okToPlaceTargetAt(barrack, row, column, horizontal)) {
                    placeTargetAt(barrack, row, column, horizontal);
                    count++;
//                    setTargetVariable(barrack, row, column, 1, 3, horizontal);
                }
            } else if(count == 7 || count == 8 || count == 9 || count == 10) {
                SentryTower sentryTower = new SentryTower(this);
                if (okToPlaceTargetAt(sentryTower, row, column, horizontal)) {
                    placeTargetAt(sentryTower, row, column, horizontal);
                    count++;
//                    setTargetVariable(sentryTower, row, column,1, 1, horizontal);
                }
            } else if(count == 11 || count == 12 || count == 13 || count == 14) {
                Tank tank = new Tank(this);
                if (okToPlaceTargetAt(tank, row, column, horizontal)) {
                    placeTargetAt(tank, row, column, horizontal);
                    count++;
//                    setTargetVariable(tank, row, column,1, 1, horizontal);
                }
            } else if (count == 15 || count == 16 || count == 17 || count == 18) {
                OilDrum oilDrum = new OilDrum(this);
                if (okToPlaceTargetAt(oilDrum, row, column, horizontal)) {
                    placeTargetAt(oilDrum, row, column, horizontal);
                    count++;
//                    setTargetVariable(oilDrum, row, column,1, 1, horizontal);
                }
            }
        }
    }

    /**
     * Sets the value of the “hit” array, “coordinate” array,
     * and “horizontal” boolean value of the target.
     */

    private void setTargetVariable(Target target, int x, int y, int row, int column, boolean horizontal) {
        target.setCoordinate(new int[] {x, y});
        target.setHorizontal(horizontal);
        if(horizontal) target.setHit(new int[row][column]);
        else target.setHit(new int[column][row]);
    }


    public boolean okToPlaceTargetAt(Target target, int row, int column, boolean horizontal) {
        String targetName = target.getTargetName().toLowerCase();
        int length = target.getLength();
        int width = target.getWidth();
//        if(horizontal) {
//            int length = target.getLength();
//            int width = target.getWidth();
//        } else {
//            int width = target.getLength();
//            int length = target.getWidth();
//        }

        if (targetName.equals("headquarter")) {
            if(horizontal) return column <= 4 ? true : false;
            else return row <= 4 ? true : false;
        } else if (targetName.equals("armory")) {
            if (row == 9 || column == 9) return false;
            if (horizontal && column > 7) return false;
            if (!horizontal && row > 7) return false;
            // determine whether there is an overlap
            return horizontal == true? ! overlapOrNot(row, column, length, width) : ! overlapOrNot(row, column, width, length);
        } else if(targetName.equals("barrack")) {
            if(horizontal && column > 7) return false;
            if(!horizontal && row > 7) return false;
            return horizontal == true? ! overlapOrNot(row, column, length, width) : ! overlapOrNot(row, column, width, length);
        } else if(targetName.equals("sentrytower")) {
            return ! overlapOrNot(row, column, length, width);
        } else {
            // if (targetName.equals("tanks") || targetName.equals("drum"))
            if (targets[row][column].getTargetName().toLowerCase().equals("ground")) return true;
            else return false;
        }
    }

    /**
     * decide whether there is an overlap
     * @param row number
     * @param column number
     * @param length of target
     * @param width of target
     * @return true if there is an overlap
     */

    private boolean overlapOrNot(int row, int column, int length, int width) {
        for(int i = row-1; i <= row + width; i++) {
            if(i < 0) continue;
            if (i > 9) return false;
            for(int j = column -1; j < column + length; j++) {
                if (j < 0) continue;
                if (j > 9) break;
                if (! targets[i][j].getTargetName().toLowerCase().equals("ground")) return true;
            }
        }
        return false;
    }

    public void placeTargetAt(Target target, int row, int column, boolean horizontal) {
        String targetName = target.getTargetName().toLowerCase();
        int length = target.getLength();
        int width = target.getWidth();

        if (horizontal) realPlace(target, row, column, length, width);
        else realPlace(target, row, column, width, length);
        setTargetVariable(target, row, column,target.getWidth(), target.getLength(), horizontal);

    }

    private void realPlace(Target target, int row, int column, int length, int width) {
//        System.out.println(row + " " + column + " " + length + " " + width);
        for (int i = row; i < row + width; i++) {
            for (int j = column; j < column + length; j++) {
//                System.out.println(i + " " + j);
                this.targets[i][j] = target;
            }
        }
    }

    /**
     * @return Returns true if the given location contains a Target(not a Ground)
     */
    public boolean isOccupied(int row, int column) {
        return !this.targets[row][column].getTargetName().toLowerCase().equals("ground");
    }

    /**
     * Attack the position specified by the row and the column.
     */
    public void shootAt(int row, int column) {
        this.targets[row][column].getShot(row, column);
    }

    /**
     * Returns true if run out of ammunition or if all targets have been
     * destroyed. Otherwise return false.
     */
    public boolean isGameOver(Weapon weapon1, Weapon weapon2) {
        // run out of animation
        if(weapon1.getShotLeft() == 0 && weapon2.getShotLeft() == 0) return true;
        // target
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                if (this.isOccupied(i,j)) {
                    if (! this.getTargetsArray()[i][j].isDestroyed()) return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns true if all targets have been destroyed.
     */
    public boolean win() {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                if (this.isOccupied(i,j)) {
                    if (! this.getTargetsArray()[i][j].isDestroyed()) return false;
                }
            }
        }
        return true;
    }


    public void print() {
        System.out.print(" ");
        for(int i = 0; i < 10; i++) {
            System.out.print(" " + i +" ");
        }
        for (int i = 0; i< 10; i++) {
            System.out.println();
            System.out.print(i + "");
            for (int j = 0; j< 10; j++) {

                if(this.targets[i][j].isHitAt(i,j)) {
                    System.out.print(" " + targets[i][j] + " ");
                } else {
                    //  a location that you have never fired upon
                    System.out.print(" . ");
                }
            }
        }
        System.out.println();
    }

    /**
     * @return the number of shots fired
     */
    public int getShotsCount() {
        return this.shotsCount;
    }


    /**
     * @return the targets array
     */
    public Target[][] getTargetsArray() {
        return this.targets;
    }

    /**
     * increase the number of shots fired
     */
    public void incrementShotsCount() {
        shotsCount += 1;
    }

    /**
     *Returns the count of destroyed targets
     */
    public int getDestroyedTargetCount() {
        return this.destroyedTargetCount;
    }

    /**
     * Set the count of destroyed targets
     */
    public void setDestroyedTargetCount(int i) {
        this.destroyedTargetCount = i;
    }

}
