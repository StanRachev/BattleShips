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

        int i, j = 0;
        for (i = 0; i < MAP_SIZE; i++) {
            for (j = 0; j < MAP_SIZE; j++) {
                mapCells[i][j] = new Cell(null, i, j);
            }
        }
    }

    public void printMap() {

        int column = 1;
        char row = 'A';
        int i = 0;
        int j = 0;

        System.out.println();
        System.out.print("  ");
        for (i = 0; i < MAP_SIZE; i++) {
            System.out.print(column++ + "  ");
        }
        System.out.println();
        for (i = 0; i < MAP_SIZE; i++) {
            if (i > 0) {
                System.out.println();
            }
            System.out.print(row++ + " "); // print from A to J
            for (j = 0; j < mapCells.length; j++) {
                if (j < 9) {
                    System.out.print(mapCells[i][j].getCellVisual() + "  ");
                } else {
                    System.out.print(mapCells[i][j].getCellVisual());
                }
            }
        }
    }

    public List<Ship> createShips() {

        List<Ship> newShip = new ArrayList<>();

        newShip.add(new Ship (true, 3, 2, 3));
        newShip.add(new Ship (false, 2, 4, 8));
        newShip.add(new Ship (true, 4, 5, 3));
        newShip.add(new Ship (false, 3, 7, 1));

        return newShip;
    }

    public void populateMap(List<Ship> ships) {

        for (Ship ship : ships) {
            int row = ship.getRow() - 1;
            int column = ship.getColumn() - 1;
            //reduce number of condition checks to 2 ( now it is 4 )
            if (ship.getSize() > 1 && ship.isHorizontal()) {
                for (int i = 0; i < ship.getSize(); i++) {
                    mapCells[row][column++].setBattleship(ship);
                }
            } else if (ship.getSize() > 1 && !ship.isHorizontal()) {
                for (int i = 0; i < ship.getSize(); i++) {
                    mapCells[row++][column].setBattleship(ship);
                }
            }
        }
    }

    public cellState_t hit(int row, int column) {

        String condition = "Hit!";
        List<Ship> myShips = createShips();
        //the only free spot to hit is "" why not just check if its == ?
        if (mapCells[row][column].getCellVisual() == '*' || mapCells[row][column].getCellVisual() == 'X') {
        //if (mapCells[row][column].getCellVisual() != '~' ) {
            System.out.println("You already shot there, try again");
        } else if (mapCells[row][column].getBattleship() != null) {
            System.out.println();
            System.out.println(condition);
            return cellState_t.HIT;
        } else {
            condition = "Miss!";
            System.out.println();
            System.out.println(condition);
            return cellState_t.MISS;
        }

        return cellState_t.INVALID;
    }

    
}