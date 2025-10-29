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


public class GameController {

    private final Board board;
    public static final int SIZE = 3;
    private int size = SIZE;
    private final Player[] players ;
    private int currentPlayerIndex = 0;
    private final ViewConsole viewConsole;
    private final InteractionUtilisateur io;
    private final Scanner scanner = new Scanner(System.in);
    private final TicTacToeModel model;



    public GameController() {

        this.players = new Player[2];
        this.size = SIZE;
        this.board = new Board(size, size);
        this.io = new InteractionUtilisateur();
        this.viewConsole = new ViewConsole();
        this.model = new TicTacToeModel(this.board);


    }

    public Board getBoard() {
        return board;
    }

    private Player getCurrentPlayer() {
        return players[currentPlayerIndex];
    }

    private void switchPlayer() {
        currentPlayerIndex = 1 - currentPlayerIndex;
    }



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

    private boolean checkWin(int x, int y) {

        return model.checkWin(x, y);
    }

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

    private void playMove(int row, int col, Player player) {
        model.playMove(row, col, player); // "X" ou "O"
    }

    protected void initBoard() {
        model.initBoard();

    }

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

        viewConsole.println("Tu commences avec : " + players[currentPlayerIndex].

                getRepresentation());

        display();


        while (true) {
            Player current = getCurrentPlayer();
            viewConsole.println("Tour de : " + current.getRepresentation());

            int[] mv;
            if (current instanceof ArtificialPlayer) {

                java.util.Random rnd = new java.util.Random();
                int n = size; // ou board.getSizeLigne()
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


    public void display() {
        viewConsole.afficherPlateau(board.toArray());
    }


    protected List<int[]> getAvailable() {
        return model.getAvailable();
    }
}

