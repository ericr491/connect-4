import javax.swing.*;
import java.awt.*;

public class DrawingBoard extends JPanel {
    private Pieces[][] board;
    private int radius = 60;
    private MouseListen listener;

    public DrawingBoard(Pieces[][] board) {
        super();
        this.board = board;
    }

    public void addListener(MouseListen listener) {
        this.listener = listener;
    }

    public void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.setColor(Color.BLACK);
        graphics.fillRect(0, 0, 700, 700);

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == null) {
                    continue;
                } else if (board[i][j].getName().equals("O")) {
                    graphics.setColor(Color.YELLOW);
                    graphics.fillOval((j * 100) + 35, (i * 100) + 50, radius, radius);
                } else if (board[i][j].getName().equals("X")) {
                    graphics.setColor(Color.RED);
                    graphics.fillOval((j * 100) + 35, (i * 100) + 50, radius, radius);
                }
            }
            if (this.listener.flag) {
                graphics.setColor(Color.white);
                graphics.setFont(new Font("TimesRoman", Font.PLAIN,  50));
                graphics.drawString(this.listener.getLastPiecePlaced().getName() + " has won", 100, 100);
            }

            if (this.listener.tie) {
                graphics.setColor(Color.white);
                graphics.setFont(new Font("TimesRoman", Font.PLAIN,  50));
                graphics.drawString("Tie!", 100, 100);
            }
        }

    }
}
