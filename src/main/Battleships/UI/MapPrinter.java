package UI;

import board.Cells.*;
import board.Map;
import board.Ship;
import game.GameManager;
import shipmanagement.ShipPlacer;

import java.util.List;
import java.util.stream.Stream;

public class MapPrinter {

    private final Map map;

    public MapPrinter(Map map) {
        this.map = map;
    }

    public void printMap(boolean isAi) {

        System.out.printf("%n  ");

        // print column numbers from 1 to 10
        Stream.iterate(1, column -> column + 1)
                .limit(map.getSize())
                .forEach(column -> System.out.print(column + "  "));
        System.out.print("\t'=' - fog");

        // print row letters from A to J
        char rowLetter = 'A';
        int row, col;
        for (row = 0; row < map.getSize(); row++) {
            System.out.printf("%n" + rowLetter++ + " ");
            // print cell visual
            for (col = 0; col < map.getSize(); col++) {
                if (isAi) {
                    ShipCell cellAi = map.getCellAi(row, col);
                    System.out.print(cellAi.getCellVisual() + "  ");
                } else {
                    ShipCell cellPlayer = map.getCellPlayer(row, col);
                    System.out.print(cellPlayer.getCellVisual() + "  ");
                }
            }
            switch (row) {
                case 0 -> System.out.print("\t'*' - miss");
                case 1 -> System.out.print("\t'@' - hit");
            }
        }
        System.out.println();
    }

    public void printUpdatedMap(List<Ship> newShip, ShipPlacer shipPlacer, ShipPrinter shipPrinter) {

        GameManager.wipeScreen();
        shipPlacer.populateMap(newShip, false);
        printMap(false);
        shipPrinter.printShips(newShip);
    }
}
