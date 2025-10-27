package fr.campus.game;


import fr.campus.view.Menu;

public class TicTacToeController extends GameController {

    public  final  static int DEFAULT_SIZE = 3;
    public TicTacToeController game;

    public TicTacToeController(Menu menu) {
        super(menu.getScanner(), DEFAULT_SIZE);
        initBoard();
    }





/*        public void setOwner ( int x, int y, Player player){
            if (x >= 0 && x < SIZE && y >= 0 && y < SIZE) {
                board[x][y].setSymbol(player.getRepresentation());
            } else {
                view.println.println("Hors limites");
            }
        }*/

}
