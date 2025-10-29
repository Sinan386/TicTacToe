package fr.campus.board;

import java.util.Scanner;

/**
 * Représente un plateau de jeu pour des jeux de type alignement (Tic-Tac-Toe, Gomoku, etc.).
 * Le plateau est constitué d'une grille de cellules de taille configurable.
 *
 * @author Campus
 * @version 1.0
 */
public class Board {
    /**
     * Grille bidimensionnelle représentant le plateau de jeu.
     */
    public Cell[][] board;

    /**
     * Nombre de lignes du plateau.
     */
    int sizeLigne;

    /**
     * Nombre de colonnes du plateau.
     */
    int sizeColonne;

    /**
     * Nombre d'alignements nécessaires pour gagner.
     * Par défaut : 3 pour Tic-Tac-Toe, 5 pour Gomoku.
     */
    private int winLength = 3;

    /**
     * Retourne le nombre d'alignements nécessaires pour gagner.
     *
     * @return le nombre d'alignements requis pour la victoire
     */
    public int getWinLength() {
        return winLength;
    }

    /**
     * Retourne la représentation du plateau sous forme de tableau bidimensionnel.
     *
     * @return le tableau de cellules représentant le plateau
     */
    public Cell[][] toArray() {
        return board;
    }

    /**
     * Constructeur du plateau de jeu.
     * Initialise un plateau avec les dimensions spécifiées.
     *
     * @param sizeligne nombre de lignes du plateau
     * @param sizecolonne nombre de colonnes du plateau
     */
    public Board(int sizeligne, int sizecolonne) {
        this.sizeLigne = sizeligne;
        this.sizeColonne = sizecolonne;
        initBoard();
    }

    /**
     * Définit le nombre d'alignements nécessaires pour gagner.
     *
     * @param k le nombre d'alignements requis (doit être compris entre 3 et
     *          la plus grande dimension du plateau)
     * @throws IllegalArgumentException si k est inférieur à 3 ou supérieur
     *                                  à la plus grande dimension du plateau
     */
    public void setWinLength(int k) {
        if (k < 3 || k > Math.max(sizeLigne, sizeColonne)) {
            throw new IllegalArgumentException("winLength invalide: " + k);
        }
        this.winLength = k;
    }

    /**
     * Vérifie si les coordonnées spécifiées sont dans les limites du plateau.
     *
     * @param x coordonnée de la ligne
     * @param y coordonnée de la colonne
     * @return true si les coordonnées sont valides, false sinon
     */
    private boolean inBounds(int x, int y) {
        return x >= 0 && x < sizeLigne && y >= 0 && y < sizeColonne;
    }

    /**
     * Retourne le nombre de lignes du plateau.
     *
     * @return le nombre de lignes
     */
    public int getSizeLigne() {
        return sizeLigne;
    }

    /**
     * Retourne le nombre de colonnes du plateau.
     *
     * @return le nombre de colonnes
     */
    public int getSizeColonne() {
        return sizeColonne;
    }

    /**
     * Initialise ou réinitialise le plateau de jeu.
     * Crée toutes les cellules vides et (ré)alloue le tableau si nécessaire.
     */
    public void initBoard() {
        // (ré)alloue le tableau si nécessaire
        if (board == null || board.length != sizeLigne || board[0].length != sizeColonne) {
            board = new Cell[sizeLigne][sizeColonne];
        }
        for (int i = 0; i < sizeLigne; i++) {
            for (int j = 0; j < sizeColonne; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    /**
     * Vérifie si le plateau est complètement rempli.
     *
     * @return true si toutes les cellules sont occupées, false sinon
     */
    public boolean isBoardFull() {
        for (int i = 0; i < sizeLigne; i++) {
            for (int j = 0; j < sizeColonne; j++) {
                if (board[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Retourne la cellule située aux coordonnées spécifiées.
     *
     * @param r index de la ligne
     * @param c index de la colonne
     * @return la cellule à la position (r, c)
     */
    public Cell getCell(int r, int c) {
        return board[r][c];
    }
}