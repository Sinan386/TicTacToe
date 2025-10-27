package fr.campus.view;


import java.util.Scanner;

public class InteractionUtilisateur {
    private final Scanner scanner = new Scanner(System.in);
    private ViewConsole viewConsole;

    public InteractionUtilisateur() {
        }

    public int askInt(String prompt) {
        while (true) {
            viewConsole.print(prompt);
            if (!scanner.hasNextInt()) {
                viewConsole.println("Entrez un nombre !");
                scanner.next();
                continue;
            }
            int val = scanner.nextInt();
            scanner.nextLine();
            return val;
        }
    }
}

