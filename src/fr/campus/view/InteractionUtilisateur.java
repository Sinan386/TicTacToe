package fr.campus.view;

import java.util.Scanner;

/**
 * Gestionnaire des interactions avec l'utilisateur via la console.
 * Fournit des méthodes pour lire différents types d'entrées utilisateur
 * avec validation et gestion des erreurs de saisie.
 *
 * @author Campus
 * @version 1.0
 */
public class InteractionUtilisateur {
    /**
     * Scanner pour lire les entrées depuis la console.
     */
    private final Scanner scanner = new Scanner(System.in);

    /**
     * Vue console pour afficher les messages d'erreur.
     */
    private ViewConsole viewConsole;

    /**
     * Constructeur par défaut de l'interaction utilisateur.
     * Initialise le scanner pour lire les entrées depuis System.in.
     */
    public InteractionUtilisateur() {
    }

    /**
     * Demande à l'utilisateur de saisir un nombre entier.
     * Affiche un message d'invite et valide que l'entrée est bien un entier.
     * En cas d'entrée invalide, affiche un message d'erreur et redemande la saisie.
     *
     * La méthode boucle jusqu'à ce qu'un entier valide soit saisi.
     *
     * @param prompt le message d'invite à afficher avant la saisie
     * @return l'entier saisi par l'utilisateur
     */
    public int askInt(String prompt) {
        while (true) {
            System.out.print(prompt);
            if (!scanner.hasNextInt()) {
                viewConsole.println("Entrez un nombre !");
                scanner.next(); // Consomme l'entrée invalide
                continue;
            }
            int val = scanner.nextInt();
            scanner.nextLine(); // Consomme le retour à la ligne restant
            return val;
        }
    }

    /**
     * Demande à l'utilisateur de saisir une ligne de texte.
     * Affiche un message d'invite et lit une ligne complète.
     *
     * @param prompt le message d'invite à afficher avant la saisie
     * @return la ligne de texte saisie par l'utilisateur (peut être vide)
     */
    public String askLine(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
}