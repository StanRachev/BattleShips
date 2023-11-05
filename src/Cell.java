public class Cell {

    private Ship battleship;
    private final int row;
    private final int column;
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
        char cellVisual = '=';
        if (isHit) {
            return '▄';
        }
        return cellVisual;
    }

    public Ship getBattleship() {
        return battleship;
    }

    public void isHit() {
        this.isHit = true;
    }

    public boolean getHit() {
        return this.isHit;
    }

}
