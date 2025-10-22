package fr.campus;

import fr.campus.main.Menu;
import fr.campus.main.TicTacToe;

public class Main {
    public static void main(String[] args) {
        TicTacToe game = new TicTacToe(new Menu());
        game.start();
    }



}
