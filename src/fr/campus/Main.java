package fr.campus;

import fr.campus.view.Menu;
import fr.campus.game.TicTacToe;

public class Main {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe(new Menu());
        game.start();
    }



}
