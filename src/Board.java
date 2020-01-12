import java.util.Scanner;

public class Board {
    private Pieces[][] board = new Pieces[6][7];  // 6 rows and 7 columns
    private int turns = 1;
    private Pieces X = new X();  // goes first
    private Pieces O = new O();

    public Board() {
        ;
    }

    public void start(Scanner reader) {
        System.out.println("X is Player 1 and goes first");
        System.out.println("O is Player 2 and goes second");

        while (true) {
            Pieces lastPieceplaced;
            System.out.println("X turn Which column do you want to place your piece?");

            try {
                int col = reader.nextInt();
                if (col < 1 || col > 7) {
                    throw new IllegalArgumentException("Only columns from 1 - 7");
                }
                lastPieceplaced = xInsert(col);
                if (won(lastPieceplaced)) {
                    System.out.println(lastPieceplaced.getName() + "  won!");
                    break;
                }
            } catch (Exception e) {
                throw new IllegalArgumentException("Please type a number");
            }

            System.out.println("O turn Which column do you want to place your piece?");

            try {
                int col = reader.nextInt();
                if (col < 1 || col > 7) {
                    throw new IllegalArgumentException("Only columns from 1 - 7");
                }
                lastPieceplaced = oInsert(col);
                if (won(lastPieceplaced)) {
                    System.out.println(lastPieceplaced.getName() + "  won!");
                    break;
                }
            } catch (Exception e) {
                throw new IllegalArgumentException("Please type a number");
            }


        }
    }

    public Pieces[][] getBoard() {
        return this.board;
    }

    public Pieces oInsert(int col) {
        Pieces o = new O();
        int row = this.emptyColumnChecker(col - 1);
        this.board[row][col - 1] = o;
        this.printBoard();
        o.setCol(col - 1);
        o.setRow(row);
        this.turns++;
        return o;
    }

    // the player is expected to type 1-7 but we minus one b/c index starts at 0
    public Pieces xInsert(int col) {
        Pieces x = new X();
        int row = this.emptyColumnChecker(col - 1);
        this.board[row][col - 1] = x;
        this.printBoard();
        x.setCol(col - 1);
        x.setRow(row);
        this.turns++;
        return x;
    }

    public int getTurns() {
        return this.turns;
    }


    // returns the lowest row that is empty in a given column
    private int emptyColumnChecker(int col) {
        for (int i = this.board.length - 1; i >= 0; i--) {
            if (this.board[i][col] == null) {
                return i;
            }
        }
        return -1;
    }

    public boolean won(Pieces p) {
        return verticalCheck(p) || leftHorizontalCheck(p) || rightHorizontalCheck(p)
                || upperDiagonalCheck(p) || lowerDiagonalCheck(p)
                || twoleftHorizontalCheck(p) || tworightHorizontalCheck(p);
    }

    private boolean twoleftHorizontalCheck(Pieces p) {
        String name = p.getName(); // "O" or "X"
        int row = p.getRow(); // [0 - 5]
        int col = p.getCol(); // [0 - 6]

        // lets say col = 4, left means decreasing
        for (int i = col; i >= col - 2; i--) {
            try {
                if (!this.board[row][i].getName().equals(name)) {
                    return false;
                }
            } catch (Exception e) {  // if index goes out of bounds
                return false;
            }
        }

        try {
            return this.board[row][col + 1].getName().equals(name);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean leftHorizontalCheck(Pieces p) {
        String name = p.getName(); // "O" or "X"
        int row = p.getRow(); // [0 - 5]
        int col = p.getCol(); // [0 - 6]

        // lets say col = 4, left means decreasing
        for (int i = col; i >= col - 3; i--) {
            try {
                if (!this.board[row][i].getName().equals(name)) {
                    return false;
                }
            } catch (Exception e) {  // if index goes out of bounds
                return false;
            }
        }

        return true;
    }

    private boolean rightHorizontalCheck(Pieces p) {
        String name = p.getName(); // "O" or "X"
        int row = p.getRow(); // [0 - 5]
        int col = p.getCol(); // [0 - 6]

        // lets say col = 4, left means decreasing
        for (int i = col; i <= col + 3; i++) {
            try {
                if (!this.board[row][i].getName().equals(name)) {
                    return false;
                }
            } catch (Exception e) {  // if index goes out of bounds
                return false;
            }
        }

        return true;
    }

    private boolean tworightHorizontalCheck(Pieces p) {
        String name = p.getName(); // "O" or "X"
        int row = p.getRow(); // [0 - 5]
        int col = p.getCol(); // [0 - 6]

        // lets say col = 4, left means decreasing
        for (int i = col; i <= col + 2; i++) {
            try {
                if (!this.board[row][i].getName().equals(name)) {
                    return false;
                }
            } catch (Exception e) {  // if index goes out of bounds
                return false;
            }
        }

        try {
            return this.board[row][col - 1].getName().equals(name);
        } catch (Exception e) {
            return false;
        }
    }

    private boolean verticalCheck(Pieces p) {
        String name = p.getName(); // "O" or "X"
        int row = p.getRow(); // [0 - 5]
        int col = p.getCol(); // [0 - 6]

        for (int i = row; i <= row + 3; i++) {
            try {
                if (!this.board[i][col].getName().equals(name)) {
                    return false;
                }
            } catch (Exception e) {  // if index goes out of bounds
                return false;
            }
        }
        return true;
    }

    private boolean upperDiagonalCheck(Pieces p) {
        String name = p.getName(); // "O" or "X"
        int row = p.getRow(); // [0 - 5]
        int col = p.getCol(); // [0 - 6]

        int rowUpcopy = row;
        int colUpcopy = col;
        int rowDowncopy = row;
        int colDowncopy = col;

        int yesCounter = -1;

        while (true) {
            try {
                if (this.board[rowUpcopy][colUpcopy].getName().equals(name)) {
                    yesCounter++;
                    rowUpcopy--;
                    colUpcopy++;
                } else
                    break;
            } catch (Exception e) {
                break;
            }

        }

        while (true) {
            try {
                if (this.board[rowDowncopy][colDowncopy].getName().equals(name)) {
                    yesCounter++;
                    rowDowncopy++;
                    colDowncopy--;
                } else
                    break;
            } catch (Exception e) {
                break;
            }

        }


        return yesCounter >= 4;
    }

    private boolean lowerDiagonalCheck(Pieces p) {
        String name = p.getName(); // "O" or "X"
        int row = p.getRow(); // [0 - 5]
        int col = p.getCol(); // [0 - 6]

        int rowUpcopy = row;
        int colUpcopy = col;
        int rowDowncopy = row;
        int colDowncopy = col;


        int yesCounter = -1;

        // two loops one for going up, the other one for going down


        while (true) {
            try {
                if (this.board[rowUpcopy][colUpcopy].getName().equals(name)) {
                    yesCounter++;
                    rowUpcopy--;
                    colUpcopy--;
                } else
                    break;
            } catch (Exception e) {
                break;
            }

        }

        while (true) {
            try {
                if (this.board[rowDowncopy][colDowncopy].getName().equals(name)) {
                    yesCounter++;
                    rowDowncopy++;
                    colDowncopy++;
                } else
                    break;
            } catch (Exception e) {
                break;
            }

        }


        return yesCounter >= 4;
    }

    private void printBoard() {
        System.out.println(" 1 2 3 4 5 6 7");
        for (int i = 0; i < this.board.length; i++) {
            this.printRow(i);
            System.out.println();
        }
    }

    private void printRow(int row) {
        for (int i = 0; i < this.board[row].length; i++) {
            if (i == 0) {
                System.out.print("[");
            }

            if (this.board[row][i] == null && i == this.board[row].length - 1) {
                System.out.print(" ]");
            } else if (this.board[row][i] == null) {
                System.out.print(" |");
            } else if (this.board[row][i] != null && i == this.board[row].length - 1) {
                System.out.print(this.board[row][i] + "]");
            } else {
                System.out.print(this.board[row][i] + "|");
            }


        }
    }

}
