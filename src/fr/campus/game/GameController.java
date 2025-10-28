package fr.campus.game;

import fr.campus.board.Board;
import fr.campus.board.Cell;
import fr.campus.player.ArtificialPlayer;
import fr.campus.player.Player;
import fr.campus.view.InteractionUtilisateur;
import java.util.ArrayList;
import java.util.List;
import fr.campus.view.ViewConsole;
import java.util.Scanner;

public class GameController {

    private final Board board;
    public static final int SIZE = 3;
    private int size = SIZE;
    private final Player[] players ;
    private int currentPlayerIndex = 0;
    private final ViewConsole viewConsole;
    private final InteractionUtilisateur io;
    private final Scanner scanner = new Scanner(System.in);


    public GameController() {

        this.players = new Player[2];
        this.size = SIZE;
        this.board = new Board(size, size);
        this.io = new InteractionUtilisateur();
        this.viewConsole = new ViewConsole();

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

        int winLength = board.getWinLength();
        String symbol = board.getCell(x,y).getSymbol();
        if (symbol.isEmpty()) return false;

        // Horizontal
        int count = 1 + countInDirection(x, y, 0, +1, symbol) +
                countInDirection(x, y, 0, -1, symbol);
        if (count >= winLength) return true;

        // Vertical
        count = 1 + countInDirection(x, y, +1, 0, symbol) + countInDirection(x, y, -1, 0, symbol);
        if (count >= winLength) return true;

        // Diagonale gauche droite
        count = 1 + countInDirection(x, y, +1, +1, symbol) + countInDirection(x, y, -1, -1, symbol);
        if (count >= winLength) return true;

        // Diagonale droite gauche
        count = 1 + countInDirection(x, y, +1, -1, symbol) + countInDirection(x, y, -1, +1, symbol);
        return count >= winLength;
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
        board.getCell(row, col).setSymbol(player.getRepresentation()); // "X" ou "O"
    }

    protected void initBoard() {
        board.initBoard();

    }

    public void start() {
        initBoard();
        String symbole = "X";


        viewConsole.println("Choisissez votre mode de jeu :  1=Humain vs IA  2=Humain vs Humain 3=IA vs IA");
        String mode = scanner.nextLine().trim().toUpperCase();
        /*if(mode.isEmpty()) mode = ;*/


        if (!mode.equals("3")) {
            symbole = "";
            while (!symbole.equals("X") && !symbole.equals("O")) {
                viewConsole.println("Choisissez votre symbole (X ou O) : ");
                symbole = scanner.nextLine().trim().toUpperCase();
                if (!symbole.equals("X") && !symbole.equals("O")) {
                    viewConsole.println("symbole invalide.");
                }
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

            int[] mv = new int[2];
            /* if (current instanceof ArtificialPlayer) { */
            mv = current.getMove();

        /*    } else {
                mv = current.getMove(); */


            if (!board.getCell(mv[0], mv[1]).isEmpty()) {
                viewConsole.println(" Cette case est déjà occupée. Réessayez.");
                continue;
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
        List<int[]> available = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board.getCell(i, j).isEmpty()) {
                    available.add(new int[]{i, j});
                }
            }
        }
        return available;
    }
}

