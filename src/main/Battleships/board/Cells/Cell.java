package board.Cells;

public class Cell {

    private final int row;
    private final int column;
    private boolean isHit = false;

    public Cell (int row, int column) {
        this.row = row;
        this.column = column;
    }

    public void markAsHit() {
        this.isHit = true;
    }

    public boolean isHit() {
        return this.isHit;
    }
}