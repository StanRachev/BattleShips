package gamestate;

import board.Cells.ShipCell;
import board.Map;

public class HitProcessor {

    public enum cellState_t {
        SHIP_HIT,
        SHIP_DESTROYED,
        MISSED,
        ALREADY_HIT
    }

    private final Map map;

    public HitProcessor(Map map) {
        this.map = map;
    }

    public cellState_t hit(int row, int col, boolean isAiCell) {

        ShipCell cell = isAiCell ? map.getCellAi(row, col) : map.getCellPlayer(row, col);

        if (cell.isHit()) {
            return cellState_t.ALREADY_HIT;
        }

        cell.markAsHit();
        if (cell.getShip() != null) {
            cell.getShip().reduceHealth(); // Take 1 health
            if (cell.getShip().getHealth() == 0) {
                cell.getShip().setSize(0); // size of Ship is 0
                return cellState_t.SHIP_DESTROYED;
            }
            return cellState_t.SHIP_HIT;
        } else {
            return cellState_t.MISSED;
        }
    }
}
