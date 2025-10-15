package fr.campus.main;

import fr.campus.board.Cell;
import fr.campus.player.Player;


public class TicTacToe {

    public static final int SIZE = 3;

    private Cell[][] board;
    private Player player;


    public TicTacToe() {
        board = new Cell[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    public void display() {
                System.out.println("---------------");
                for (int i = 0; i < SIZE; i++) {
                    System.out.print("|");
                    for (int j = 0; j < SIZE; j++) {
                        System.out.print("   |");
                    }
                    System.out.println();
                    System.out.println("--------------");


            }

        }
    }

