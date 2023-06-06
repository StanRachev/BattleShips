import java.util.ArrayList;
import java.util.List;

public class Map {
    public enum cellState_t {
        HIT,
        KILL,
        MISS,
        INVALID
    };
    private final int MAP_SIZE = 10;
    private Cell[][] mapCells = new Cell[MAP_SIZE][MAP_SIZE];

    public void createMap() {

        int i, j;

        for (i = 0; i < MAP_SIZE; i++) {
            for (j = 0; j < MAP_SIZE; j++) {
                mapCells[i][j] = new Cell(null, i, j);
            }
        }
    }

    public void printMap() {

        int column = 1;
        char row = 'A';
        int i, j;

        System.out.println();
        System.out.print("  ");
        for (i = 0; i < MAP_SIZE; i++) {
            System.out.print(column++ + "  "); // print numbers
        }
        System.out.println();
        for (i = 0; i < MAP_SIZE; i++) {
            if (i > 0) {
                System.out.println(); // new line for every new row
            }
            System.out.print(row++ + " "); // print from A to J
            for (j = 0; j < mapCells.length; j++) {
                System.out.print(mapCells[i][j].getCellVisual() + "  "); // print water '~' for every cell
            }
        }
        System.out.println();
    }

    public List<Ship> createShips() {

        List<Ship> newShip = new ArrayList<>();

        newShip.add(new Ship (true, 3, 2, 3));
        newShip.add(new Ship (false, 2, 4, 8));
//        newShip.add(new Ship (true, 4, 5, 3));
//        newShip.add(new Ship (false, 3, 7, 1));

        return newShip; // return list of ships
    }

    public void populateMap(List<Ship> ships) {

        for (Ship ship : ships) {
            int row = ship.getRow() - 1;
            int column = ship.getColumn() - 1;

            if (ship.getSize() > 1) {
                if (ship.isHorizontal()) {
                    for (int i = 0; i < ship.getSize(); i++) {
                        mapCells[row][column++].setBattleship(ship);
                    }
                } else {
                    for (int i = 0; i < ship.getSize(); i++) {
                        mapCells[row++][column].setBattleship(ship);
                    }
                }
            }
        }
    }

    public cellState_t hit(int row, int column) {

        String condition = "Hit!";
        Cell cellHit = mapCells[row][column];
        if (cellHit.getHit()) {
            System.out.println();
            System.out.println("You already shot here");
        } else if (cellHit.getBattleship() != null) {
            cellHit.isHit();
            cellHit.getBattleship().reduceHealth(); // Take 1 health

            if (cellHit.getBattleship().getHealth() != 0) { // if ship is hit, but not destroyed
                System.out.println();
                System.out.println(condition); // Hit!
            } else {
                condition = "Kill!";
                System.out.println(condition);
                cellHit.getBattleship().setSize(0); // size of Ship is 0
                if (cellHit.getBattleship().getHealth() == 0) {
                    return cellState_t.KILL;
                }
            }

            return cellState_t.HIT;
        } else {
            cellHit.isHit();
            condition = "Miss!";
            System.out.println();
            System.out.println(condition);

            return cellState_t.MISS;
        }

        return cellState_t.INVALID;
    }

    public boolean allDestroyed(List<Ship> myShips) {

        for (Ship ship : myShips) {
            if (!ship.isDead()) {
                break;
            } else {
                System.out.println("Game over!");
                return true;
            }
        }

        return false;
    }

    public void printShips(List<Ship> myShips) {

        int cntr;

        System.out.println();
        for (int i = 1; i <= 4; i++) {
            cntr = 0;
            for (Ship myShip : myShips) {
                if (myShip.getHealth() == 0) {
                    myShip.setSize(0);
                }
                if (myShip.getSize() == i) {
                    cntr++;
                }
            }
            System.out.println("Ships of size " + i + " = " + cntr);
        }
    }
}