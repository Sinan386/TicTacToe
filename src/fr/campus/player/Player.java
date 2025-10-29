package fr.campus.player;

import fr.campus.view.InteractionUtilisateur;
import fr.campus.view.ViewConsole;
import fr.campus.controller.TicTacToeController;

/**
 * Représente un joueur humain dans le jeu.
 * Gère l'interaction avec l'utilisateur pour récupérer les coups à jouer.
 * Chaque joueur est identifié par un symbole unique (par exemple "X" ou "O").
 *
 * Cette classe peut être étendue pour créer différents types de joueurs
 * (par exemple {@link ArtificialPlayer} pour une IA).
 *
 * @author Campus
 * @version 1.0
 * @see ArtificialPlayer
 */
public class Player {
    /**
     * Le symbole représentant ce joueur sur le plateau (par exemple "X" ou "O").
     */
    private final String representation;

    /**
     * Gestionnaire des interactions avec l'utilisateur pour récupérer les entrées.
     */
    private final InteractionUtilisateur io;

    /**
     * Vue console pour afficher les messages au joueur.
     */
    private final ViewConsole viewConsole;

    /**
     * Constructeur du joueur.
     * Initialise un joueur avec son symbole et les composants d'interaction.
     *
     * @param representation le symbole du joueur (par exemple "X" ou "O")
     * @param io le gestionnaire d'interactions utilisateur pour lire les entrées
     * @param viewConsole la vue console pour afficher les messages
     */
    public Player(String representation, InteractionUtilisateur io, ViewConsole viewConsole) {
        this.representation = representation;
        this.io = io;
        this.viewConsole = viewConsole;
    }

    /**
     * Retourne le symbole représentant ce joueur.
     *
     * @return le symbole du joueur (par exemple "X" ou "O")
     */
    public String getRepresentation() {
        return representation;
    }

    /**
     * Demande au joueur de choisir son prochain coup.
     * Interagit avec l'utilisateur pour obtenir les coordonnées (ligne, colonne)
     * et valide que les coordonnées sont dans les limites du plateau.
     *
     * La méthode boucle jusqu'à ce que des coordonnées valides soient entrées.
     * La validation de la disponibilité de la case (case vide) doit être effectuée
     * par le contrôleur de jeu.
     *
     * @return un tableau d'entiers [ligne, colonne] représentant le coup choisi,
     *         avec des valeurs comprises entre 0 et SIZE-1
     */
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