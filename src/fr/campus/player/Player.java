package fr.campus.player;

import java.util.Scanner;

import fr.campus.main.TicTacToe;
import fr.campus.board.Cell;


import org.w3c.dom.ls.LSOutput;

public class Player {
    private String representation = "X";
    private final Scanner scanner;


    public Player(String representation, Scanner scanner) {
        this.representation = representation;
        this.scanner = scanner;
    }


    public String getRepresentation() {
        return representation;
    }

    public int[] getMove() {
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
                continue;}

            colonne = scanner.nextInt();
            scanner.nextLine();


            if (ligne < 0 || ligne >= TicTacToe.SIZE || colonne < 0 || colonne >= TicTacToe.SIZE) {
                System.out.println("Coordonnées hors du plateau. Réessayez.");
                continue;
            }

            break;
        }
        return new int[]{ligne, colonne};

    }


}

