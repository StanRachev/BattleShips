public class Ship {

    private boolean isHorizontal;
    private boolean isDead = false;
    private boolean isAi;
    private int size;
    private int health;
    private int row;
    private int column;
    private int cntr = 0;
    private char cellVisualVanguard = 'V';
    private char cellVisualTriumpf = 'T';
    private char cellVisualHercules = 'H';
    private char cellVisualDreadnought = 'D';
    private char cellVisualDestroyed = 'X';

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
        return cellVisualVanguard;
    }

    public char getCellVisualDestroyed() {
        return cellVisualDestroyed;
    }

    public char getCellVisualTriumpf() {
        return cellVisualTriumpf;
    }

    public char getCellVisualHercules() {
        return cellVisualHercules;
    }

    public char getCellVisualDreadnought() {
        return cellVisualDreadnought;
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