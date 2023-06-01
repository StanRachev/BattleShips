public class Ships {

    private boolean isHorizontal;
    private int size;
    private int row;
    private int column;

    public Ships(int size, boolean isHorizontal, int row, int column) {
        this.size = size;
        this.isHorizontal = isHorizontal;
        this.row = row;
        this.column = column;
    }

    public int getSize() {
        return size;
    }
}