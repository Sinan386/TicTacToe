package fr.campus.controller;

/**
 * Contrôleur spécialisé pour le jeu Tic-Tac-Toe.
 * Hérite de {@link GameController} et utilise ses fonctionnalités de base
 * pour gérer une partie de Tic-Tac-Toe classique (grille 3x3).
 *
 * Cette classe peut être étendue pour ajouter des fonctionnalités
 * spécifiques au Tic-Tac-Toe si nécessaire.
 *
 * @author Campus
 * @version 1.0
 * @see GameController
 */
public class TicTacToeController extends GameController {

    /**
     * Constructeur du contrôleur Tic-Tac-Toe.
     * Initialise le contrôleur avec les paramètres par défaut
     * du jeu Tic-Tac-Toe (plateau 3x3, 3 alignements pour gagner).
     */
    public TicTacToeController() {
        super();
    }


}