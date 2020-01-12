public class X implements Pieces {
    private String name = "X";
    private int col;
    private int row;

    public X() {
        ;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public String getName() {
        return this.name;
    }

    public int getCol() {
        return this.col;
    }

    public int getRow() {
        return this.row;
    }

    public String toString() {
        return "X";
    }

}
