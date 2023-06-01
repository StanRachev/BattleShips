public class Ships {

    private boolean isDead = false;
    private String state = "Hit";
    private int remainingShips = 10;

    public String createShips(int row, int column) {

        Map map = new Map();
        char[][] ship = new char[10][10];

        ship[1][7] = 'V';
        ship[2][1] = 'V'; ship[2][3] = 'V'; ship[2][4] = 'V'; ship[2][5] = 'V';
        ship[3][8] = 'V';
        ship[4][8] = 'V';
        ship[5][0] = 'V'; ship[5][1] = 'V'; ship[5][3] = 'V'; ship[5][5] = 'V';
        ship[6][3] = 'V';
        ship[7][3] = 'V'; ship[7][8] = 'V';
        ship[9][3] = 'V'; ship[9][5] = 'V'; ship[9][6] = 'V'; ship[9][7] = 'V'; ship[9][8] = 'V';

        if (ship[row][column] == 'V') {
            System.out.println(state);
            map.createMap(row, column);
        } else {
            System.out.println("Miss");
        }

        return state;
    }
}