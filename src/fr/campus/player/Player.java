package fr.campus.player;

import fr.campus.view.InteractionUtilisateur;
import fr.campus.view.ViewConsole;
import fr.campus.game.TicTacToeController;

public class Player {
    private final String representation;
    private final InteractionUtilisateur io;
    private final ViewConsole viewConsole;


    public Player(String representation, InteractionUtilisateur io, ViewConsole viewConsole) {
        this.representation = representation;
        this.io = io;
        this.viewConsole = viewConsole;
    }



    public String getRepresentation() {
        return representation;
    }

    public int[] getMove() {
        while (true) {
            int ligne = io.askInt("Entre le numéro de la ligne (0, 1 ou 2) : ");
            int colonne = io.askInt("Entre le numéro de la colonne (0, 1 ou 2) : ");

            if (ligne < 0 || ligne >= TicTacToeController.SIZE || colonne < 0 || colonne >= TicTacToeController.SIZE) {
                viewConsole.println("Coordonnées hors du plateau. Réessayez.");
                continue;
            }
            return new int[]{ligne, colonne};
        }
    }
}


