public class Cell {

    private char cellVisual = '=';
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
            if (!this.battleship.isAi()) {
                if (battleship.getSize() == 2) {
                    return this.battleship.getCellVisualVanguard();
                }
                if (battleship.getSize() == 3) {
                    return this.battleship.getCellVisualTriumpf();
                }
                if (battleship.getSize() == 4) {
                    return this.battleship.getCellVisualHercules();
                }
                if (battleship.getSize() == 5) {
                    return this.battleship.getCellVisualDreadnought();
                }
            }
        }
        if (isHit) {
            if (this.cellVisual == '=') {
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
