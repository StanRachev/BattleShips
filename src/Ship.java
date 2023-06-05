public class Ship {

    private boolean isHorizontal;
    private int size;
    private int healt = size;
    private int row;
    private int column;
    private char cellVisualAlive = 'V'; // implement it in here for "dead", "alive" etc?

    public Ship(boolean isHorizontal, int size, int row, int column) {
        this.isHorizontal = isHorizontal;
        this.size = size;
        this.row = row;
        this.column = column;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }
    public int getSize() {
        return size;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public char getCellVisualAlive() {
        return cellVisualAlive;
    }

    public int getHealt() {
        return healt;
    }

    public void setHealt() {
        healt--;
    }
}