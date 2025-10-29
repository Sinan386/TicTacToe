package fr.campus.view;

import java.util.Scanner;

/**
 * Gestionnaire du scanner console partagé pour l'application.
 * Fournit un point d'accès unique à un {@link Scanner} connecté à {@code System.in}
 * afin d'éviter la création de multiples scanners sur l'entrée standard.
 *
 * @author Campus
 * @version 1.0
 */
public class Menu {
    /**
     * Scanner unique pour lire les entrées depuis la console.
     */
    private final Scanner scanner;

    /**
     * Constructeur par défaut.
     * Initialise le scanner en le connectant à {@code System.in}.
     */
    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Retourne l'instance de {@link Scanner} utilisée par l'application.
     *
     * @return le scanner connecté à {@code System.in}
     */
    public Scanner getScanner() {
        return scanner;
    }
}
