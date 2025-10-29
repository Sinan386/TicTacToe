package fr.campus.player;

import java.util.Random;
import fr.campus.controller.GameController;

/**
 * Représente un joueur contrôlé par l'ordinateur (Intelligence Artificielle).
 * Cette implémentation utilise une stratégie de jeu aléatoire simple.
 * Hérite de {@link Player} et surcharge la méthode de récupération des coups
 * pour générer des mouvements automatiques.
 *
 * @author Campus
 * @version 1.0
 * @see Player
 */
public class ArtificialPlayer extends Player {

    /**
     * Générateur de nombres aléatoires utilisé pour choisir les coups.
     */
    private Random random;

    /**
     * Constructeur du joueur artificiel.
     * Initialise un joueur IA avec le symbole spécifié.
     * Les paramètres d'interaction utilisateur sont définis à null car l'IA
     * ne nécessite pas d'interaction humaine.
     *
     * @param representation le symbole du joueur (par exemple "X" ou "O")
     */
    public ArtificialPlayer(String representation) {
        super(representation, null, null);
        this.random = new Random();
    }

    /**
     * Génère un coup de manière aléatoire.
     * Choisit aléatoirement une ligne et une colonne dans les limites du plateau.
     *
     * Note : Cette implémentation ne vérifie pas si la case est déjà occupée.
     * La validation du coup doit être effectuée par le contrôleur de jeu.
     *
     * @return un tableau d'entiers [ligne, colonne] représentant le coup choisi
     */
    @Override
    public int[] getMove() {
        int i = random.nextInt(GameController.SIZE);
        int j = random.nextInt(GameController.SIZE);
        return new int[]{i, j};
    }
}