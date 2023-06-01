import java.util.Arrays;

public class Map {
    private int MAP_SIZE = 10;
    private boolean startGame = true;
    private final char[][] map = new char[MAP_SIZE][MAP_SIZE];

    public void createMap(int userRow, int userColumn) {
/*        if (userRow != -1 && userColumn != -1) {
            map[userRow][userColumn] = 'X';
        }*/
        int i , j = 0;
        for ( i = 6; i < MAP_SIZE; i++) {
            for ( j = 0; j < MAP_SIZE; j++) {
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
        for ( i = 0; i < MAP_SIZE; i++) {
            System.out.print(column++ + "  ");
        }
        System.out.println();
        for ( i = 0; i < MAP_SIZE; i++) {
            System.out.print(row++ + " ");
            System.out.println(Arrays.toString(map[i]));
        }
    }
}