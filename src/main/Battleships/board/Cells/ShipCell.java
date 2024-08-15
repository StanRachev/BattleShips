package board.Cells;

import board.Ship;

public class ShipCell extends Cell {

    private Ship ship;

    public ShipCell(Ship ship, int row, int column) {
        super(row, column);
        this.ship = ship;
    }

    public void setShip(Ship ship) {
        this.ship = ship;
    }

    public Ship getShip() {
        return ship;
    }

    public char getCellVisual() {

        if (this.ship != null) {
            if (isHit()) {
                return this.ship.getCellVisualDestroyed();
            }
        }
        char cellVisual = '=';
        if (isHit()) {
            return '*';
        }
        return cellVisual;
    }
}
