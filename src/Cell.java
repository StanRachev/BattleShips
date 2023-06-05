public class Cell {

    private char cellVisual = '~';
    private Ship battleship;
    private int row;
    private int column;

    public Cell (Ship batleShip, int row, int column) {
        this.battleship = batleShip;
        this.row = row;
        this.column = column;
    }

    public void setBattleship(Ship battleship) {
        this.battleship = battleship;
    }

    public char getCellVisual() {
        if (this.battleship != null) {
            return this.battleship.getCellVisualAlive();
        }
        return cellVisual;
    }

    public Ship getBattleship() {
        return battleship;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }
}
