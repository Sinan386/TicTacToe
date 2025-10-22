package fr.campus.main;

import fr.campus.board.Cell;
import fr.campus.player.ArtificialPlayer;
import fr.campus.player.Player;

import java.util.Scanner;


public class TicTacToe {
    public static final int SIZE = 3;

    private final Cell[][] board = new Cell[SIZE][SIZE];
    private final Scanner scanner;
    private final Player[] players = {new Player("X"), new ArtificialPlayer("O")};
    private int currentPlayerIndex = 0;

    public Cell[][] getBoard() {
        return board;
    }


    private Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    private void switchPlayer() {
        currentPlayerIndex = 1 - currentPlayerIndex;
    }


    private boolean isBoardFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isOver() {
        if (isBoardFull()) {
            System.out.println("Match nul !");
            return true;
        }

        for (int x = 0; x < SIZE; x++) {
            for (int y = 0; y < SIZE; y++) {

                if (!board[x][y].isEmpty() && checkWin(x, y)) {
                    System.out.println("Le joueur " + board[x][y].getSymbol() + " a gagné !");
                    return true;
                }
            }
        }
        if (isBoardFull()) {
            System.out.println("Match nul !");
            return true;
        }
        return false;
    }


    private boolean checkWin(int x, int y) {
        String symbol = board[x][y].getSymbol();
        if (symbol.isEmpty()) return false;

        // Horizontal
        int count = 1 + countInDirection(x, y, 0, +1, symbol) + countInDirection(x, y, 0, -1, symbol);
        if (count >= 3) return true;

        // Vertical
        count = 1 + countInDirection(x, y, +1, 0, symbol) + countInDirection(x, y, -1, 0, symbol);
        if (count >= 3) return true;

        // Diagonale gauche droite
        count = 1 + countInDirection(x, y, +1, +1, symbol) + countInDirection(x, y, -1, -1, symbol);
        if (count >= 3) return true;

        // Diagonale droite gauche
        count = 1 + countInDirection(x, y, +1, -1, symbol) + countInDirection(x, y, -1, +1, symbol);
        return count >= 3;
    }

    private int countInDirection(int x, int y, int dx, int dy, String symbol) {
        int count = 0;
        int newX = x + dx;
        int newY = y + dy;

        while (newX >= 0 && newX < SIZE && newY >= 0 && newY < SIZE && board[newX][newY].getSymbol().equals(symbol)) {
            count++;
            newX += dx;
            newY += dy;
        }

        return count;
    }


    private void playMove(int row, int col, Player player) {
        board[row][col].setSymbol(player.getRepresentation()); // "X" ou "O"
    }

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
        if (symbole.equals("X")) {
            currentPlayerIndex = 0;
        } else {
            currentPlayerIndex = 1;
        }

        System.out.println("Tu commences avec : " + players[currentPlayerIndex].getRepresentation());

        display();

        while (true) {
            Player current = getCurrentPlayer();
            System.out.println("Tour de : " + current.getRepresentation());


            int[] mv = current.getMove();
            playMove(mv[0], mv[1], current);
            display();

            if (!board[mv[0]][mv[1]].isEmpty()) {
                System.out.println(" Cette case est déjà occupée. Réessayez.");
                continue;
            }



            if (checkWin(mv[0], mv[1])) {
                System.out.println("Le joueur " + current.getRepresentation() + " a gagné !");
                break;
            }
            if (isBoardFull()) {
                System.out.println("Match nul !");
                break;
            }

            switchPlayer();
        }
    }





/*        public void setOwner ( int x, int y, Player player){
            if (x >= 0 && x < SIZE && y >= 0 && y < SIZE) {
                board[x][y].setSymbol(player.getRepresentation());
            } else {
                System.out.println("Hors limites");
            }
        }*/

        public void display () {
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
