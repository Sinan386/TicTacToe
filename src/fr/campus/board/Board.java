package fr.campus.board;

import java.util.Scanner;

public class Board {
    public Cell[][] board;
    int sizeLigne;
    int sizeColonne;

    public Board(int sizeligne, int sizecolonne) {
        this.sizeLigne = sizeligne;
        this.sizeColonne = sizecolonne;
        initboard();

    }

    private void initboard() {

        board = new Cell[sizeLigne][sizeColonne];
        for (int i = 0; i < sizeLigne; i++) {
            for (int j = 0; j < sizeColonne; j++) {
                board[i][j] = new Cell();
            }
        }



    }


}
