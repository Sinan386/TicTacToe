package fr.campus.player;

import java.util.Scanner;

import fr.campus.view.InteractionUtilisateur;
import fr.campus.view.View;
import fr.campus.game.TicTacToe;

public class Player {
    private final String representation;
    private final InteractionUtilisateur io;
    private final View view;


    public Player(String representation, InteractionUtilisateur io, View view) {
        this.representation = representation;
        this.io = io;
        this.view = view;
    }



    public String getRepresentation() {
        return representation;
    }

    public int[] getMove() {
        while (true) {
            int ligne = io.lireInt("Entre le numéro de la ligne (0, 1 ou 2) : ", view);
            int colonne = io.lireInt("Entre le numéro de la colonne (0, 1 ou 2) : ", view);

            if (ligne < 0 || ligne >= TicTacToe.SIZE || colonne < 0 || colonne >= TicTacToe.SIZE) {
                view.println("Coordonnées hors du plateau. Réessayez.");
                continue;
            }
            return new int[]{ligne, colonne};
        }
    }
}


