package fr.campus.view;

import java.util.Scanner;


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
