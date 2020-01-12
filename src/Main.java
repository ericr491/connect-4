public class Main {

    public static void main(String[] args) {
        Board board = new Board();

        UI ui = new UI(board);
        ui.run();
    }

}