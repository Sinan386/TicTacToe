package fr.campus.view;

import fr.campus.board.Cell;

/**
 * Vue console de l'application.
 * Fournit des méthodes utilitaires pour afficher du texte et
 * représenter un plateau de jeu dans la console.
 *
 * @author Campus
 * @version 1.0
 */
public class ViewConsole {

    /**
     * Affiche une ligne de texte suivie d'un saut de ligne.
     *
     * @param text le texte à afficher
     */
    public void println(String text) {
        System.out.println(text);
    }

    /**
     * Affiche un texte sans saut de ligne final.
     *
     * @param text le texte à afficher
     */
    public void print(String text) {
        System.out.print(text);
    }

    /**
     * Affiche le plateau de jeu sous forme de grille dans la console.
     * Chaque cellule est rendue via sa représentation textuelle
     * retournée par {@code Cell#getRepresentation()}.
     *
     * @param board le plateau à afficher (matrice de cellules)
     */
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
