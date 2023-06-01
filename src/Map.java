import java.util.Arrays;
import java.util.List;

public class Map {
    public enum cellState_t {
        HIT,
        KILL,
        MISS,
        INVALID
    };
    private final int MAP_SIZE = 10;
    private final char[][] map = new char[MAP_SIZE][MAP_SIZE];

    public void createMap() {

        int i, j = 0;
        for (i = 0; i < MAP_SIZE; i++) {
            for (j = 0; j < MAP_SIZE; j++) {
                map[i][j] = '~';
            }
        }
    }

    public void printMap() {

        int column = 1;
        char row = 'A';
        int i = 0;
        int j = 0;

        System.out.print("  ");
        for (i = 0; i < MAP_SIZE; i++) {
            System.out.print(column++ + "  ");
        }
        System.out.println();
        for (i = 0; i < MAP_SIZE; i++) {
            System.out.print(row++ + " "); // print from A to J
            System.out.println(Arrays.toString(map[i]));
        }
    }

    public List<char[]> createShips() {

        char[][] ship = new char[10][10];

        ship[1][7] = 'V';
        ship[2][1] = 'V';
        ship[2][3] = 'V'; ship[2][4] = 'V'; ship[2][5] = 'V';
        ship[3][8] = 'V'; ship[4][8] = 'V';
        ship[5][0] = 'V'; ship[5][1] = 'V';
        ship[5][5] = 'V';
        ship[5][3] = 'V'; ship[6][3] = 'V'; ship[7][3] = 'V';
        ship[7][8] = 'V';
        ship[9][3] = 'V';
        ship[9][5] = 'V'; ship[9][6] = 'V'; ship[9][7] = 'V'; ship[9][8] = 'V';

        return List.of(ship);
    }

    public void addShip(List<char[]> shipsList) {

        char[][] shipsArray = shipsList.toArray(new char[MAP_SIZE][MAP_SIZE]);

        int i, j = 0;
        for (i = 0; i < MAP_SIZE; i++) {
            for (j = 0; j < MAP_SIZE; j++) {
                if (shipsArray[i][j] == 'V') {
                    map[i][j] = shipsArray[i][j];
                }
            }
        }
    }

    public cellState_t hit(int row, int column) {

        String condition = "Hit";

        if (map[row][column] == 'V') {
            System.out.println(condition);
            map[row][column] = 'X';
            return cellState_t.HIT;
        } else {
            condition = "Miss!";
            System.out.println(condition);
            map[row][column] = '*';
            return cellState_t.MISS;
        }

//        return cellState_t.INVALID;
    }
}