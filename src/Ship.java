public class Ship {

    private final boolean isHorizontal;
    private boolean isDead = false;
    private final boolean isAi;
    private int size;
    private int health;
    private final int row;
    private final int column;

    public Ship(boolean isAi, boolean isHorizontal, int size, int row, int column) {
        this.isAi = isAi;
        this.isHorizontal = isHorizontal;
        this.size = size;
        this.row = row;
        this.column = column;
        this.health = size;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public boolean isDead() {
        if (this.health == 0) {
            this.isDead = true;
        }
        return isDead;
    }
    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public char getCellVisualVanguard() {
        return 'V';
    }

    public char getCellVisualDestroyed() {
        return '@';
    }

    public char getCellVisualTriumpf() {
        return 'T';
    }

    public char getCellVisualHercules() {
        return 'H';
    }

    public char getCellVisualDreadnought() {
        return 'D';
    }

    public int getHealth() {
        return health;
    }

    public void reduceHealth() {
        health--;
    }

    public boolean isAi() {
        return isAi;
    }
}