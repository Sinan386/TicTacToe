package fr.campus.view;


import java.util.Scanner;

public class InteractionUtilisateur {
    private final Scanner scanner;

    public InteractionUtilisateur(Scanner scanner) {
        this.scanner = scanner;
    }

    public int lireInt(String prompt, View view) {
        while (true) {
            view.print(prompt);
            if (!scanner.hasNextInt()) {
                view.println("Entrez un nombre !");
                scanner.next();
                continue;
            }
            int val = scanner.nextInt();
            scanner.nextLine();
            return val;
        }
    }
}

