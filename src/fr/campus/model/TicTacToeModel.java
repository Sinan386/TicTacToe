package fr.campus.model;

import fr.campus.board.Board;
import fr.campus.player.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Modèle contenant la logique métier du jeu Tic-Tac-Toe.
 * Gère les règles du jeu, la validation des coups et la détection des victoires.
 * Supporte les variantes avec différentes tailles de plateau et conditions de victoire
 * (Tic-Tac-Toe classique 3x3, Gomoku 5 en ligne, etc.).
 *
 * @author Campus
 * @version 1.0
 */
public class TicTacToeModel {

    /**
     * Le plateau de jeu associé à ce modèle.
     */
    private final Board board;

    /**
     * Constructeur du modèle Tic-Tac-Toe.
     *
     * @param board le plateau de jeu à gérer
     */
    public TicTacToeModel(Board board) {
        this.board = board;
    }

    /**
     * Retourne le plateau de jeu.
     *
     * @return le plateau de jeu associé à ce modèle
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Initialise ou réinitialise le plateau de jeu.
     * Vide toutes les cellules pour préparer une nouvelle partie.
     */
    public void initBoard() {
        board.initBoard();
    }

    /**
     * Vérifie si le plateau est complètement rempli.
     *
     * @return true si toutes les cases sont occupées, false sinon
     */
    public boolean isBoardFull() {
        return board.isBoardFull();
    }

    /**
     * Effectue un coup sur le plateau.
     * Place le symbole du joueur à la position spécifiée.
     *
     * @param row ligne où placer le symbole (indexée à partir de 0)
     * @param col colonne où placer le symbole (indexée à partir de 0)
     * @param player le joueur qui effectue le coup
     */
    public void playMove(int row, int col, Player player) {
        board.getCell(row, col).setSymbol(player.getRepresentation());
    }

    /**
     * Vérifie si un coup est valide.
     * Un coup est valide s'il est dans les limites du plateau et si la case est vide.
     *
     * @param r ligne du coup à vérifier
     * @param c colonne du coup à vérifier
     * @return true si le coup est valide, false sinon
     */
    public boolean isValidMove(int r, int c) {
        int maxR = board.getSizeLigne();
        int maxC = board.getSizeColonne();
        if (r < 0 || r >= maxR || c < 0 || c >= maxC) return false;
        return board.getCell(r, c).isEmpty();
    }

    /**
     * Vérifie si le coup joué à la position donnée entraîne une victoire.
     * Examine les quatre directions possibles : horizontale, verticale et les deux diagonales.
     *
     * @param x coordonnée ligne du dernier coup joué
     * @param y coordonnée colonne du dernier coup joué
     * @return true si ce coup forme un alignement gagnant, false sinon
     */
    public boolean checkWin(int x, int y) {
        int winLength = board.getWinLength();
        String symbol = board.getCell(x, y).getSymbol();
        if (symbol == null || symbol.isEmpty()) return false;

        // Horizontal
        int count = 1 + countInDirection(x, y, 0, +1, symbol)
                + countInDirection(x, y, 0, -1, symbol);
        if (count >= winLength) return true;

        // Vertical
        count = 1 + countInDirection(x, y, +1, 0, symbol)
                + countInDirection(x, y, -1, 0, symbol);
        if (count >= winLength) return true;

        // Diagonale descendante (\)
        count = 1 + countInDirection(x, y, +1, +1, symbol)
                + countInDirection(x, y, -1, -1, symbol);
        if (count >= winLength) return true;

        // Diagonale ascendante (/)
        count = 1 + countInDirection(x, y, +1, -1, symbol)
                + countInDirection(x, y, -1, +1, symbol);
        return count >= winLength;
    }

    /**
     * Compte le nombre de symboles identiques consécutifs dans une direction donnée.
     * Utilisé pour détecter les alignements lors de la vérification de victoire.
     *
     * @param x coordonnée ligne de départ
     * @param y coordonnée colonne de départ
     * @param dx déplacement en ligne à chaque itération (-1, 0, ou +1)
     * @param dy déplacement en colonne à chaque itération (-1, 0, ou +1)
     * @param symbol le symbole à rechercher et compter
     * @return le nombre de symboles consécutifs identiques dans la direction spécifiée
     */
    public int countInDirection(int x, int y, int dx, int dy, String symbol) {
        int count = 0;
        int newX = x + dx;
        int newY = y + dy;

        int maxR = board.getSizeLigne();
        int maxC = board.getSizeColonne();

        while (newX >= 0 && newX < maxR
                && newY >= 0 && newY < maxC
                && board.getCell(newX, newY).getSymbol().equals(symbol)) {
            count++;
            newX += dx;
            newY += dy;
        }
        return count;
    }

    /**
     * Retourne la liste de toutes les positions disponibles (cases vides) sur le plateau.
     * Utile pour l'implémentation d'une IA ou pour afficher les coups possibles.
     *
     * @return liste des coordonnées [ligne, colonne] des cases vides
     */
    public List<int[]> getAvailable() {
        List<int[]> available = new ArrayList<>();
        int maxR = board.getSizeLigne();
        int maxC = board.getSizeColonne();
        for (int i = 0; i < maxR; i++) {
            for (int j = 0; j < maxC; j++) {
                if (board.getCell(i, j).isEmpty()) {
                    available.add(new int[]{i, j});
                }
            }
        }
        return available;
    }
}