package fr.campus.main;

import java.util.Scanner;
import fr.campus.player.Player;


/**
 * Create a unique scanner for the all app
 */
public class Menu {
    private final Scanner scanner;

    public Menu() {
        this.scanner = new Scanner(System.in);
    }

    public Scanner getScanner() {
        return scanner;
    }
}
