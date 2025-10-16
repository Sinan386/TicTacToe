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
    }


    public int[] getMoveFromPlayer(int x, int y) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Entre le numéro de la ligne (0, 1 où 2) ");
        int ligne = scanner.nextInt();

        System.out.println("Entre le numéro de la colonne (0, 1 où 2) ");
        int colonne = scanner.nextInt();

        if (ligne < 0 || ligne > (SIZE - 1) || colonne < 0 || colonne > (SIZE - 1)) {
            System.out.println(" Entre un chiffre valide ! 0, 1 où 2");
        }
        return new int[]{ligne, colonne};

    }

    public void setOwner(int x, int y, Player player) {
        if (x >= 0 && x < SIZE && y >= 0 && y < SIZE) {
            board[x][y].setSymbol(player.getRepresentation());
        }else{
            System.out.println("Hors limites");

        }



    }

    public void display() {
        System.out.println("---------------");
        for (int i = 0; i < SIZE; i++) {
            System.out.print("|");
            for (int j = 0; j < SIZE; j++) {
                System.out.print("   |");
            }
            System.out.println();
            System.out.println("--------------");


        }

    }
}

