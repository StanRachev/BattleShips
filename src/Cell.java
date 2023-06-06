public class Cell {

    private char cellVisual = '~';
    private char cellVisualHitWater = '*';
    private Ship battleship;
    private int row;
    private int column;
    private boolean isHit = false;

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
            if (isHit) {
                return this.battleship.getCellVisualDestroyed();
            }
            return this.battleship.getCellVisualAlive();
        }
        if (isHit) {
            if (this.cellVisual == '~') {
                return this.cellVisualHitWater;
            }
        }
        return cellVisual;
    }

    public void setCellVisual(char cellVisual) {
        this.cellVisual = cellVisual;
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

    public void isHit() {
        this.isHit = true;
    }

    public boolean getHit() {
        return this.isHit;
    }
}
