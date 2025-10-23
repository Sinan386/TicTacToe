package fr.campus;

import fr.campus.board.Cell;
import fr.campus.InteractionUtilisateur;

public class View {

    public void println(String text) {
        System.out.println(text);
    }

    public void print(String text) {
        System.out.print(text);
    }

    public void afficherPlateau(Cell[][] board) {
        System.out.println(" ------------- ");
        for (int i = 0; i < board.length; i++) {
            System.out.print("|");
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j].getRepresentation() + "|");
            }
            System.out.println();
            System.out.println(" ------------- ");
        }
    }
}
