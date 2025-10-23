package fr.campus.main;

import fr.campus.board.Cell;
import fr.campus.player.ArtificialPlayer;
import fr.campus.player.Player;
import fr.campus.InteractionUtilisateur;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import fr.campus.View;


public class Game {
    public static final int SIZE = 3;
    protected final Scanner scanner;
    private final Cell[][] board;
    private int size;
    private final Player[] players = new Player[2];
    private int currentPlayerIndex = 0;
    private final View view = new View();


    public Game(Scanner scanner, int size) {
        this.scanner = scanner;
        this.size = size;
        this.board = new Cell[size][size];
    }

    public Cell[][] getBoard() {
        return board;
    }

    private Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    private void switchPlayer() {
        currentPlayerIndex = 1 - currentPlayerIndex;
    }

    protected boolean isBoardFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isOver() {
        if (isBoardFull()) {
            view.println("Match nul !");
            return true;
        }

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {

                if (!board[x][y].isEmpty() && checkWin(x, y)) {
                    view.println("Le joueur " + board[x][y].getSymbol() + " a gagné !");
                    return true;
                }
            }
        }
        if (isBoardFull()) {
            view.println("Match nul !");
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

        while (newX >= 0 && newX < size && newY >= 0 && newY < size && board[newX][newY].getSymbol().equals(symbol)) {
            count++;
            newX += dx;
            newY += dy;
        }

        return count;
    }

    private void playMove(int row, int col, Player player) {
        board[row][col].setSymbol(player.getRepresentation()); // "X" ou "O"
    }

    protected void initBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    public void start() {
        String symbole = "X";

        view.println("Choisissez votre mode de jeu :  1=Humain vs IA  2=Humain vs Humain 3=IA vs IA");
        String mode = scanner.nextLine().trim().toUpperCase();
        /*if(mode.isEmpty()) mode = ;*/


        if (!mode.equals("3")) {
            symbole = "";
            while (!symbole.equals("X") && !symbole.equals("O")) {
                view.println("Choisissez votre symbole (X ou O) : ");
                symbole = scanner.nextLine().trim().toUpperCase();
                if (!symbole.equals("X") && !symbole.equals("O")) {
                    view.println("symbole invalide.");
                }
            }
        }


        switch (mode) {
            case "1":
                if (symbole.equals("X")) {
                    players[0] = new Player("X", scanner, view);
                    players[1] = new ArtificialPlayer("O");
                } else {
                    players[0] = new Player("O", scanner, view);
                    players[1] = new ArtificialPlayer("X");
                }
                currentPlayerIndex = 0;
                view.println("J1" + players[0].getRepresentation() + " J2" + players[1].getRepresentation());
                break;

            case "2":
                if (symbole.equals("X")) {
                    players[0] = new Player("X", scanner, view);
                    players[1] = new Player("O", scanner, view);
                } else {
                    players[0] = new Player("O", scanner, view);
                    players[1] = new Player("X", scanner, view);
                    currentPlayerIndex = 0;
                    view.println("Tu joues avec : " + players[0].getRepresentation());
                    break;
                }
            case "3":
                if (symbole.equals("X")) {
                    players[0] = new ArtificialPlayer("X");
                    players[1] = new ArtificialPlayer("O");
                } else {
                    players[0] = new ArtificialPlayer("O");
                    players[1] = new ArtificialPlayer("X");
                    currentPlayerIndex = 0;
                    view.println("Que le meilleur gagne : ");
                    break;
                }


        }


        currentPlayerIndex = 0;

        view.println("Tu commences avec : " + players[currentPlayerIndex].

                getRepresentation());

        display();


        while (true) {
            Player current = getCurrentPlayer();
            view.println("Tour de : " + current.getRepresentation());

            int[] mv = new int[2];
            /* if (current instanceof ArtificialPlayer) { */
            mv = current.getMove();

        /*    } else {
                mv = current.getMove(); */


            if (!board[mv[0]][mv[1]].isEmpty()) {
                view.println(" Cette case est déjà occupée. Réessayez.");
                continue;
            }

            playMove(mv[0], mv[1], current);
            display();


            if (checkWin(mv[0], mv[1])) {
                view.println("Le joueur " + current.getRepresentation() + " a gagné !");
                break;
            }
            if (isBoardFull()) {
                view.println("Match nul !");
                break;
            }

            switchPlayer();

        }
    }


    public void display() {
        view.afficherPlateau(board);
    }


    protected List<int[]> getAvailable() {
        List<int[]> available = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j].isEmpty()) {
                    available.add(new int[]{i, j});
                }
            }
        }
        return available;
    }
}

