package callOfDuty;

/**
 * Abstract class Target represents a generic (or abstract) Target.
 */
public abstract class Target {
    /**
     * An array of length 2 that specifies the coordinate of the head of a
     * target. Head means the upper left part of a Target.
     */
    private int[]coordinate = new int[2];

    /**
     * private int length
     */
    private int length;

    /**
     * The width of the Target
     * width <= length
     */
    private int width;

    /**
     * Indicates whether the Target is horizontal or not
     */
    private boolean horizontal;

    /**
     * An array of the same size as the target, indicating the number of
     * times a part of the target has been hit.
     */
    private int[][] hit;

    /**
     * An instance of Base that the target is placed in.
     */
    private Base base;

    /**
     * decide whether a target is destroyed
     */
    public boolean destroy = false;

    /**
     * constructor
     * @param length of target
     * @param width of target
     * @param base of target
     */
    public Target(int length, int width, Base base) {
        this.length = length;
        this.width = width;
        this.base = base;
    }

    /**
     * @Returns the coordinate array
     */
    public int[] getCoordinate() {
        return this.coordinate;
    }

    /**
     * @return whether the target is horizontal or not
     */
    public boolean getHorizontal() {
        return this.horizontal;
    }

    /**
     * @returns the hit array
     */
    public int[][] getHit() {
        return this.hit;
    }

    /**
     * @return the base
     */
    public Base getBase() {
        return this.base;
    }

    /**
     * @return the length of this target
     */
    public int getLength() {
        return this.length;
    }

    /**
     * @return the width of this target
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Sets the coordinate array
     * @param coordinate
     */
    void setCoordinate(int[] coordinate) {
        this.coordinate[0] = coordinate[0];
        this.coordinate[1] = coordinate[1];
    }

    /**
     * Sets the value of horizontal
     */
    void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    /**
     * Sets the value of hit array
     * @param hit
     */
    void setHit(int[][] hit) {
        int row = hit.length;
        int column = hit[0].length;
        this.hit = new int[row][column];
        for(int i = 0; i <  row; i++) {
            for (int j = 0; j < column; j++) {
                this.hit[i][j] += hit[i][j];
            }
        }
    }

    /**
     *Defines the behavior when a target is destroyed. Some may explode,
     * while some do nothing.
     */
    abstract void explode();

    /**
     * Returns the type of Target as a String.
     * @return the type of Target
     */
    public abstract String getTargetName();

    /**
     * If a part of the Target occupies the given row and column and it is not
     * destroyed, mark that part of the Target as “hit” (in the hit array, index
     * (0,0) indicates the head).
     * @param row number
     * @param column number
     */
    public void getShot (int row, int column) {
        this.hit[row - this.coordinate[0]][column - this.coordinate[1]] += 1;
        if(this.isDestroyed() && this.destroy == false && !this.getTargetName().equals("ground")) {
            this.destroy = true;
            System.out.println("You have destroyed a " + this.getTargetName());
            this.explode();
        }
    }

    /**
     * Decide whether the target is destroyed or not
     * @return true if every part of the Target has been hit, false otherwise.
     */
    public boolean isDestroyed() {
        int row = this.hit.length;
        int column = this.hit[0].length;
        for(int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (this.getHit()[i][j] < 1) return false;
            }
        }
        return true;
    }

    /**
     * Returns true if the target has been hit at the given coordinate.
     * @param row number
     * @param column number
     * @return  true if the target has been hit at the given coordinate.
     */
    public boolean isHitAt(int row, int column) {
        if (this.hit[row - this.coordinate[0]][column - this.coordinate[1]] > 0) {
            return true;
        }
        return false;
    }

    /**
     * ”X” if the Target has been destroyed
     * and ”O” (capital letter O) if it has not been destroyed.
     */
    public String toString() {
        if (this.isDestroyed()) return "X";
        else return "O";
    }
}
