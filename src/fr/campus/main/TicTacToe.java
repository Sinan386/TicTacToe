package fr.campus.main;

import fr.campus.board.Cell;
import fr.campus.player.Player;

import java.util.Scanner;


public class TicTacToe {
    public static final int SIZE = 3;

    private final Cell[][] board = new Cell[SIZE][SIZE];
    private final Scanner scanner;


    public TicTacToe() {
        Menu menu = new Menu();
        this.scanner = menu.getScanner();
        initBoard();
    }

    private void initBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = new Cell();
            }
        }
    }


    public void start() {
        String symbole = "";


        while (!symbole.equals("X") && !symbole.equals("O")) {
            System.out.print("Choisissez votre symbole (X ou O) : ");
            symbole = scanner.nextLine().trim().toUpperCase();

            if (!symbole.equals("X") && !symbole.equals("O")) {
                System.out.println("symbole invalide.");
            }
        }
        Player player = new Player(symbole);
        System.out.println("Tu joues avec : " + player.getRepresentation());
        display();
        while (true) {
            int[] mv = getMoveFromPlayer();
            setOwner(mv[0], mv[1], player);
            display();
        }
    }


    public int[] getMoveFromPlayer() {
        int ligne = -1;
        int colonne = -1;

        while (true) {
            System.out.print("Entre le numéro de la ligne (0, 1 ou 2) : ");
            if (!scanner.hasNextInt()) {
                System.out.println("Entrez un nombre !");
                scanner.next();
                continue;
            }
            ligne = scanner.nextInt();

            System.out.print("Entre le numéro de la colonne (0, 1 ou 2) : ");
            if (!scanner.hasNextInt()) {
                System.out.println("Entrez un nombre !");
                scanner.next();
                continue;
            }
            colonne = scanner.nextInt();
            scanner.nextLine();

            if (ligne < 0 || ligne >= SIZE || colonne < 0 || colonne >= SIZE) {
                System.out.println("Coordonnées hors du plateau. Réessayez.");
                continue;
            }
            if (!board[ligne][colonne].isEmpty()) {
                System.out.println("Cette case est déjà occupée. Réessayez.");
                continue;
            }
            break;
        }
        return new int[]{ligne, colonne};
    }

    public void setOwner(int x, int y, Player player) {
        if (x >= 0 && x < SIZE && y >= 0 && y < SIZE) {
            board[x][y].setSymbol(player.getRepresentation());
        } else {
            System.out.println("Hors limites");
        }
    }

    public void display() {
        System.out.println(" ------------- ");
        for (int i = 0; i < SIZE; i++) {
            System.out.print("|");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j].getRepresentation() + "|");
            }
            System.out.println();
            System.out.println(" ------------- ");
        }
    }
}
