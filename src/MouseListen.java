import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseListen implements MouseListener {
    private DrawingBoard c;
    private Board b;
    private Pieces lastPiecePlaced;
    public boolean flag = false;
    public boolean tie = false;


    public MouseListen(DrawingBoard c, Board b) {
        this.c = c;
        this.b = b;
    }

    private int whenPressed(int x) {
        // assuming 0 < x < 700, note the coords are based on the ui position on the monitor
        return (x / 100) + 1;
    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        int x = e.getXOnScreen();
        int newx = whenPressed(x);

        if (this.b.getTurns() % 2 == 1) {
            this.lastPiecePlaced = this.b.xInsert(newx);
        } else {
            this.lastPiecePlaced = this.b.oInsert(newx);
        }

        // this tells drawingboard to update itself
        this.c.repaint();

        if (this.b.won(this.lastPiecePlaced)) {
            this.flag = true;
        }

        if (this.b.getTurns() >= 43 && !flag) {
            this.tie = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public Pieces getLastPiecePlaced() {
        return this.lastPiecePlaced;
    }

}