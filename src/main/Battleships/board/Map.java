package board;

import board.Cells.ShipCell;

public class Map {

    private final ShipCell[][] cellsPlayer;
    private final ShipCell[][] cellsAi;
    private final int MAP_SIZE;

    public Map(int size) {
        MAP_SIZE = size;
        cellsPlayer = new ShipCell[MAP_SIZE][MAP_SIZE];
        cellsAi = new ShipCell[MAP_SIZE][MAP_SIZE];
    }

    public void initializeCells(boolean isAi) {
        int row, col;
        for (row = 0; row < MAP_SIZE; row++) {
            for (col = 0; col < MAP_SIZE; col++) {
                if (isAi) {
                    cellsAi[row][col] = new ShipCell(null, row, col);
                } else {
                    cellsPlayer[row][col] = new ShipCell(null, row, col);
                }
            }
        }
    }

    public ShipCell getCellPlayer(int row, int col) {
        return cellsPlayer[row][col];
    }

    public ShipCell getCellAi(int row, int col) {
        return cellsAi[row][col];
    }

    public int getSize() {
        return MAP_SIZE;
    }
}
