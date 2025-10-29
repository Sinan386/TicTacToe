package fr.campus.controller;

import fr.campus.board.Board;
import fr.campus.player.ArtificialPlayer;
import fr.campus.player.Player;
import fr.campus.view.InteractionUtilisateur;
import java.util.ArrayList;
import java.util.List;
import fr.campus.view.ViewConsole;
import java.util.Scanner;
import fr.campus.model.TicTacToeModel;

/**
 * Contrôleur principal du jeu Tic-Tac-Toe.
 * Gère le déroulement de la partie, l'alternance des joueurs et les conditions de victoire.
 * Supporte différents modes de jeu : Humain vs IA, Humain vs Humain, IA vs IA.
 *
 * @author Campus
 * @version 1.0
 */
public class GameController {

    /**
     * Le plateau de jeu.
     */
    private final Board board;

    /**
     * Taille par défaut du plateau (3x3 pour le Tic-Tac-Toe classique).
     */
    public static final int SIZE = 3;

    /**
     * Taille actuelle du plateau.
     */
    private int size = SIZE;

    /**
     * Tableau contenant les deux joueurs de la partie.
     */
    private final Player[] players;

    /**
     * Index du joueur actuellement en train de jouer (0 ou 1).
     */
    private int currentPlayerIndex = 0;

    /**
     * Vue console pour l'affichage.
     */
    private final ViewConsole viewConsole;

    /**
     * Gestionnaire des interactions avec l'utilisateur.
     */
    private final InteractionUtilisateur io;

    /**
     * Scanner pour lire les entrées utilisateur.
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Modèle contenant la logique métier du jeu.
     */
    private final TicTacToeModel model;

    /**
     * Constructeur du contrôleur de jeu.
     * Initialise le plateau, les composants de vue et le modèle.
     */
    public GameController() {
        this.players = new Player[2];
        this.size = SIZE;
        this.board = new Board(size, size);
        this.io = new InteractionUtilisateur();
        this.viewConsole = new ViewConsole();
        this.model = new TicTacToeModel(this.board);
    }

    /**
     * Retourne le plateau de jeu.
     *
     * @return le plateau de jeu actuel
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Retourne le joueur dont c'est actuellement le tour.
     *
     * @return le joueur actif
     */
    private Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    /**
     * Alterne entre les deux joueurs.
     * Passe du joueur 0 au joueur 1, ou inversement.
     */
    private void switchPlayer() {
        currentPlayerIndex = 1 - currentPlayerIndex;
    }

    /**
     * Vérifie si la partie est terminée.
     * Une partie se termine soit par une victoire, soit par un match nul (plateau plein).
     *
     * @return true si la partie est terminée, false sinon
     */
    public boolean isOver() {
        if (board.isBoardFull()) {
            viewConsole.println("Match nul !");
            return true;
        }

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                if (!board.getCell(x,y).isEmpty() && checkWin(x, y)) {
                    viewConsole.println("Le joueur " + board.getCell(x,y).getSymbol() + " a gagné !");
                    return true;
                }
            }
        }
        if (board.isBoardFull()) {
            viewConsole.println("Match nul !");
            return true;
        }
        return false;
    }

    /**
     * Vérifie si le coup joué à la position donnée entraîne une victoire.
     * Délègue la vérification au modèle.
     *
     * @param x coordonnée ligne du dernier coup joué
     * @param y coordonnée colonne du dernier coup joué
     * @return true si ce coup est gagnant, false sinon
     */
    private boolean checkWin(int x, int y) {
        return model.checkWin(x, y);
    }

    /**
     * Compte le nombre de symboles identiques dans une direction donnée.
     * Utilisé pour vérifier les alignements.
     *
     * @param x coordonnée ligne de départ
     * @param y coordonnée colonne de départ
     * @param dx déplacement en ligne (-1, 0, ou 1)
     * @param dy déplacement en colonne (-1, 0, ou 1)
     * @param symbol le symbole à compter
     * @return le nombre de symboles consécutifs dans la direction spécifiée
     */
    private int countInDirection(int x, int y, int dx, int dy, String symbol) {
        int count = 0;
        int newX = x + dx;
        int newY = y + dy;

        while (newX >= 0 && newX < size
                && newY >= 0 && newY < size
                && board.getCell(newX, newY).getSymbol().equals(symbol)) {
            count++;
            newX += dx;
            newY += dy;
        }

        return count;
    }

    /**
     * Effectue un coup sur le plateau.
     * Place le symbole du joueur à la position spécifiée.
     *
     * @param row ligne où jouer le coup
     * @param col colonne où jouer le coup
     * @param player le joueur qui joue le coup
     */
    private void playMove(int row, int col, Player player) {
        model.playMove(row, col, player);
    }

    /**
     * Initialise ou réinitialise le plateau de jeu.
     * Prépare le plateau pour une nouvelle partie.
     */
    protected void initBoard() {
        model.initBoard();
    }

    /**
     * Lance une nouvelle partie.
     * Gère la sélection du mode de jeu, la configuration des joueurs,
     * et la boucle principale du jeu jusqu'à ce qu'une condition de fin soit atteinte.
     *
     * Modes disponibles :
     * - 1 : Humain vs IA
     * - 2 : Humain vs Humain
     * - 3 : IA vs IA
     */
    public void start() {
        initBoard();
        String symbole = "X";
        String mode = null;

        while (mode == null || !(mode.equals("1") || mode.equals("2") || mode.equals("3"))) {
            viewConsole.println("Choisissez votre mode de jeu :  1=Humain vs IA  2=Humain vs Humain  3=IA vs IA");
            viewConsole.print("> ");
            mode = scanner.nextLine().trim();
        }

        if (!mode.equals("3")) {
            symbole = null;
            while (symbole == null || !(symbole.equals("X") || symbole.equals("O"))) {
                viewConsole.println("Choisissez votre symbole (X ou O) : ");
                viewConsole.print("> ");
                symbole = scanner.nextLine().trim().toUpperCase();
            }
        }

        switch (mode) {
            case "1":
                if (symbole.equals("X")) {
                    players[0] = new Player("X", io, viewConsole);
                    players[1] = new ArtificialPlayer("O");
                } else {
                    players[0] = new Player("O", io, viewConsole);
                    players[1] = new ArtificialPlayer("X");
                }
                currentPlayerIndex = 0;
                viewConsole.println("J1" + players[0].getRepresentation() + " J2" + players[1].getRepresentation());
                break;

            case "2":
                if (symbole.equals("X")) {
                    players[0] = new Player("X", io, viewConsole);
                    players[1] = new Player("O", io, viewConsole);
                } else {
                    players[0] = new Player("O", io, viewConsole);
                    players[1] = new Player("X", io, viewConsole);
                    currentPlayerIndex = 0;
                    viewConsole.println("Tu joues avec : " + players[0].getRepresentation());
                }
                break;

            case "3":
                if (symbole.equals("X")) {
                    players[0] = new ArtificialPlayer("X");
                    players[1] = new ArtificialPlayer("O");
                } else {
                    players[0] = new ArtificialPlayer("O");
                    players[1] = new ArtificialPlayer("X");
                    currentPlayerIndex = 0;
                    viewConsole.println("Que le meilleur gagne : ");
                }
                break;
        }

        currentPlayerIndex = 0;

        viewConsole.println("Tu commences avec : " + players[currentPlayerIndex].getRepresentation());

        display();

        while (true) {
            Player current = getCurrentPlayer();
            viewConsole.println("Tour de : " + current.getRepresentation());

            int[] mv;
            if (current instanceof ArtificialPlayer) {
                java.util.Random rnd = new java.util.Random();
                int n = size;
                while (true) {
                    int r = rnd.nextInt(n);
                    int c = rnd.nextInt(n);
                    if (model.isValidMove(r, c)) {
                        mv = new int[]{r, c};
                        break;
                    }
                }
            } else {
                while (true) {
                    mv = current.getMove();
                    if (mv == null || mv.length != 2) {
                        viewConsole.println("Format de coordonnées invalide. Réessayez.");
                        continue;
                    }
                    if (!model.isValidMove(mv[0], mv[1])) {
                        viewConsole.println(" Cette case est déjà occupée OU hors plateau. Réessayez.");
                        continue;
                    }
                    break;
                }
            }

            playMove(mv[0], mv[1], current);
            display();

            if (checkWin(mv[0], mv[1])) {
                viewConsole.println("Le joueur " + current.getRepresentation() + " a gagné !");
                break;
            }
            if (board.isBoardFull()) {
                viewConsole.println("Match nul !");
                break;
            }

            switchPlayer();
        }
    }

    /**
     * Affiche l'état actuel du plateau dans la console.
     */
    public void display() {
        viewConsole.afficherPlateau(board.toArray());
    }

    /**
     * Retourne la liste des positions disponibles (cases vides) sur le plateau.
     *
     * @return liste des coordonnées [ligne, colonne] des cases vides
     */
    protected List<int[]> getAvailable() {
        return model.getAvailable();
    }
}