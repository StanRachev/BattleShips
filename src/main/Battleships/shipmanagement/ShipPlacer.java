package shipmanagement;

import board.Map;
import board.Ship;

import java.util.List;

public class ShipPlacer {
    private final Map map;

    public ShipPlacer(Map map) {
        this.map = map;
    }

    public void populateMap(List<Ship> ships, boolean isAi) {

        for (Ship ship : ships) {
            int row = ship.getRow();
            int column = ship.getColumn();

            if (ship.getSize() >= 1) {
                if (ship.isHorizontal()) {
                    for (int i = 0; i < ship.getSize(); i++) {
                        if (isAi) {
                            map.getCellAi(row, column++).setShip(ship);
                        } else {
                            map.getCellPlayer(row, column++).setShip(ship);
                        }
                    }
                } else {
                    for (int i = 0; i < ship.getSize(); i++) {
                        if (isAi) {
                            map.getCellAi(row++, column).setShip(ship);
                        } else {
                            map.getCellPlayer(row++, column).setShip(ship);
                        }
                    }
                }
            }
        }
    }
}
