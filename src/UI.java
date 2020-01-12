import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseListener;

public class UI implements Runnable {
    private JFrame frame;
    private DrawingBoard draw;
    private Board board;
    private MouseListen listener;

    public UI(Board board) {
        this.board = board;
    }

    @Override
    public void run() {
        this.frame = new JFrame("Connect4");
        frame.setPreferredSize(new Dimension(700,700));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.createComponents(this.frame);

        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        this.draw = new DrawingBoard(board.getBoard());
        container.add(draw);
        this.listener = new MouseListen(this.draw, this.board);
        frame.addMouseListener(this.listener);
        draw.addListener(this.listener);
    }

    private MouseListen getListener() {
        return this.listener;
    }
}
