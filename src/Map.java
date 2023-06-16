import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Map {
    public enum cellState_t {
        Ship_is_hit,
        Ship_is_destroyed,
        missed,
        Already_hit
    };
    private final int MAP_SIZE = 10;
    private final Cell[][] mapCells = new Cell[MAP_SIZE][MAP_SIZE];

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
        System.out.print("   '=' - fog");
        System.out.println();
        for (i = 0; i < MAP_SIZE; i++) {
            if (i > 0) {
                System.out.println(); // new line for every new row
            }
            System.out.print(row++ + " "); // print from A to J
            for (j = 0; j < mapCells.length; j++) {
                System.out.print(mapCells[i][j].getCellVisual() + "  "); // print fog '=' for every cell
            }
            switch (i) {
                case 0 -> System.out.print("    '*' - miss");
                case 1 -> System.out.print("    'X' - hit");
            }
        }
        System.out.println();
    }

    public List<Ship> createShips(int shipPlacement) {

        List<Ship> newShip = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        Random random = new Random();

        int row = 0;
        int column = 0;
        int size = 2;

        boolean isShip = false;
        boolean isHorizontal = false;
        boolean isNumber = false;
        while (!isNumber) {

            if ((newShip.size() == 5 || newShip.size() == 7 || newShip.size() == 9) && isShip) {
                size++;
            }

            if (shipPlacement == 1) {

                Player player = new Player();
                GameLauncher.wipeScreen();
                populateMap(newShip);
                printMap();
                printShips(newShip);

                switch (size) {
                    case 2 -> System.out.println("\nPlace 5x Vanguard(2 cells)");
                    case 3 -> System.out.println("\nPlace 2x Triumph(3 cells)");
                    case 4 -> System.out.println("\nPlace 2x Hercules(4 cells)");
                    case 5 -> System.out.println("\nPlace 1x Dreadnought(5 cells)");
                }

                int[] playerChoice = player.makeGuess();
                System.out.print("For horizontal press 1, for vertical press 2: ");
                int isHorizontalUser = scan.nextInt();
                isHorizontal = isHorizontalUser == 1;
                row = playerChoice[0];
                column = playerChoice[1];

            } else {

                int trueFalse = random.nextInt(11);
                if (trueFalse % 2 == 0) {
                    isHorizontal = true;
                }
                row = random.nextInt(10);
                column = random.nextInt(10);
            }

            if (isHorizontal) {
                if (column + (size - 1) > MAP_SIZE - 1) {
                    if (shipPlacement == 1) {
                        continue;
                    } else {
                        column -= ((column + (size - 1)) - (MAP_SIZE - 1));
                    }
                }
            } else { // Not horizontal
                if (row + (size - 1) > MAP_SIZE - 1) {
                    if (shipPlacement == 1) {
                        continue;
                    } else {
                        row -= ((row + (size - 1)) - (MAP_SIZE - 1));
                    }
                }
            }
            isShip = true;
            for (Ship ships : newShip) {
                int shipRow = ships.getRow();
                int shipColumn = ships.getColumn();
                int shipSize = ships.getSize();

                if (isHorizontal && ships.isHorizontal()) {
                    if (row == shipRow) {
                        for (int j = 0; j < size; j++) {
                            if (column == shipColumn) {
                                isShip = false;
                                break;
                            }
                            column++;
                        }
                        if (!isShip) {
                            break;
                        }
                        column -= size;
                    }
                } else if (!isHorizontal && ships.isHorizontal()) {
                    for (int j = 0; j < size; j++) {
                        if (row == shipRow) {
                            for (int k = 0; k < shipSize; k++) {
                                if (column == shipColumn) {
                                    isShip = false;
                                    break;
                                }
                                shipColumn++;
                            }
                            if (!isShip) {
                                break;
                            }
                        }
                        row++;
                    }
                    if (!isShip) {
                        break;
                    }
                    row -= size;
                } else if (isHorizontal && !ships.isHorizontal()) {
                    for (int j = 0; j < shipSize; j++) {
                        if (row == shipRow) {
                            for (int k = 0; k < size; k++) {
                                if (column == shipColumn) {
                                    isShip = false;
                                    break;
                                }
                                column++;
                            }
                            if (!isShip) {
                                break;
                            }
                            column -= size;
                        }
                        shipRow++;
                    }
                    if (!isShip) {
                        break;
                    }
                } else if (!isHorizontal && !ships.isHorizontal()) {
                    if (column == ships.getColumn()) {
                        for (int j = 0; j < size; j++) {
                            if (row == shipColumn) {
                                isShip = false;
                                break;
                            }
                            row++;
                        }
                        if (!isShip) {
                            break;
                        }
                        row -= size;
                    }
                }
            }
            if (isShip) {
                newShip.add(new Ship(false, isHorizontal, size, row, column));
            }
            if (newShip.size() == 10) {
                isNumber = true;
            }
        }

        return newShip; // return list of ships
    }

    public void populateMap(List<Ship> ships) {

        for (Ship ship : ships) {
            int row = ship.getRow();
            int column = ship.getColumn();

            if (ship.getSize() >= 1) {
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

        Cell cellHit = mapCells[row][column];
        if (cellHit.getHit()) {
            return cellState_t.Already_hit;
        } else if (cellHit.getBattleship() != null) {
            cellHit.isHit();
            cellHit.getBattleship().reduceHealth(); // Take 1 health
            if (cellHit.getBattleship().getHealth() != 0) { // if ship is hit, but not destroyed
                return cellState_t.Ship_is_hit;
            } else {
                cellHit.getBattleship().setSize(0); // size of Ship is 0
                if (cellHit.getBattleship().getHealth() == 0) {
                    return cellState_t.Ship_is_destroyed;
                }
            }
            return cellState_t.Ship_is_hit;
        } else {
            cellHit.isHit();
            return cellState_t.missed;
        }
    }

    public void printShips(List<Ship> myShips) {

        int cntr;
        String shipName = "";

        System.out.println();
        for (int i = 2; i <= 5; i++) {
            switch (i) {
                case 2 -> shipName = "Vanguard ";
                case 3 -> shipName = "Triumph ";
                case 4 -> shipName = "Hercules ";
                case 5 -> shipName = "Dreadnought";
            }
            cntr = 0;
            for (Ship myShip : myShips) {
                if (myShip.getHealth() == 0) {
                    myShip.setSize(0);
                }
                if (myShip.getSize() == i) {
                    cntr++;
                }
            }
            System.out.printf("(%d)%s     \t   %d %s%n", i,  shipName, cntr, "ships");
        }
    }
}

class MapAI extends Map {

    private final int MAP_SIZE = 10;
    private final Cell[][] mapCellsAI = new Cell[MAP_SIZE][MAP_SIZE];

    @Override
    public void createMap() {

        int i, j;

        for (i = 0; i < MAP_SIZE; i++) {
            for (j = 0; j < MAP_SIZE; j++) {
                mapCellsAI[i][j] = new Cell(null, i, j);
            }
        }
    }
    @Override
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
            for (j = 0; j < mapCellsAI.length; j++) {
                System.out.print(mapCellsAI[i][j].getCellVisual() + "  "); // print fog '=' for every cell
            }
        }
        System.out.println();
    }

    @Override
    public List<Ship> createShips(int shipPlacement) {

        List<Ship> newShipAi = new ArrayList<>();
        Random random = new Random();

        int size = 2;
        boolean isNumber = false;
        boolean isShip = false;
        while (!isNumber) {

            boolean isHorizontal = false;
            int row = random.nextInt(10);
            int column = random.nextInt(10);

            if ((newShipAi.size() == 5 || newShipAi.size() == 7 || newShipAi.size() == 9) && isShip) {
                size++;
            }
            int trueFalse = random.nextInt(11);
            if (trueFalse % 2 == 0) {
                isHorizontal = true;
                if (column + (size - 1) > MAP_SIZE - 1) {
                    column -= ((column + (size - 1)) - (MAP_SIZE - 1));
                }
            } else {
                if (row + (size - 1) > MAP_SIZE - 1) {
                    row -= ((row + (size - 1)) - (MAP_SIZE - 1));
                }
            }
            isShip = true;
            for (Ship ships : newShipAi) {
                int shipRow = ships.getRow();
                int shipColumn = ships.getColumn();
                int shipSize = ships.getSize();

                if (size == 1 && shipSize == 1) {
                    if (row == shipRow && column == shipColumn) {
                        isShip = false;
                        break;
                    }
                } else if (isHorizontal && (ships.isHorizontal() || shipSize == 1)) {
                    if (row == shipRow) {
                        for (int j = 0; j < size; j++) {
                            if (column == shipColumn) {
                                isShip = false;
                                break;
                            }
                            column++;
                        }
                        if (!isShip) {
                            break;
                        }
                        column -= size;
                    }
                } else if (!isHorizontal && (ships.isHorizontal() || shipSize == 1)) {
                    for (int j = 0; j < size; j++) {
                        if (row == shipRow) {
                            for (int k = 0; k < shipSize; k++) {
                                if (column == shipColumn) {
                                    isShip = false;
                                    break;
                                }
                                shipColumn++;
                            }
                            if (!isShip) {
                                break;
                            }
                        }
                        row++;
                    }
                    if (!isShip) {
                        break;
                    }
                    row -= size;
                } else if (isHorizontal && !ships.isHorizontal()) {
                    for (int j = 0; j < shipSize; j++) {
                        if (row == shipRow) {
                            for (int k = 0; k < size; k++) {
                                if (column == shipColumn) {
                                    isShip = false;
                                    break;
                                }
                                column++;
                            }
                            if (!isShip) {
                                break;
                            }
                            column -= size;
                        }
                        shipRow++;
                    }
                    if (!isShip) {
                        break;
                    }
                } else if (!isHorizontal && !ships.isHorizontal()) {
                    if (column == ships.getColumn()) {
                        for (int j = 0; j < size; j++) {
                            if (row == shipColumn) {
                                isShip = false;
                                break;
                            }
                            row++;
                        }
                        if (!isShip) {
                            break;
                        }
                        row -= size;
                    }
                }
            }
            if (isShip) {
                newShipAi.add(new Ship(true, isHorizontal, size, row, column));
            }
            if (newShipAi.size() == 10) {
                isNumber = true;
            }
        }

        return newShipAi; // return list of ships
    }

    @Override
    public void populateMap(List<Ship> ships) {

        for (Ship ship : ships) {
            int row = ship.getRow();
            int column = ship.getColumn();

            if (ship.getSize() >= 1) {
                if (ship.isHorizontal()) {
                    for (int i = 0; i < ship.getSize(); i++) {
                        mapCellsAI[row][column++].setBattleship(ship);
                    }
                } else {
                    for (int i = 0; i < ship.getSize(); i++) {
                        mapCellsAI[row++][column].setBattleship(ship);
                    }
                }
            }
        }
    }

    @Override
    public cellState_t hit(int row, int column) {

        Cell cellHit = mapCellsAI[row][column];
        if (cellHit.getHit()) {
            return cellState_t.Already_hit;
        } else if (cellHit.getBattleship() != null) {
            cellHit.isHit();
            cellHit.getBattleship().reduceHealth(); // Take 1 health
            if (cellHit.getBattleship().getHealth() != 0) { // if ship is hit, but not destroyed
                return cellState_t.Ship_is_hit;
            } else {
                cellHit.getBattleship().setSize(0); // size of Ship is 0
                if (cellHit.getBattleship().getHealth() == 0) {
                    return cellState_t.Ship_is_destroyed;
                }
            }
            return cellState_t.Ship_is_hit;
        } else {
            cellHit.isHit();
            return cellState_t.missed;
        }
    }
}