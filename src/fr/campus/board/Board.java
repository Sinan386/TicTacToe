package fr.campus.board;

import java.util.Scanner;

public class Board {
    public Cell[][] board;
    int sizeLigne;
    int sizeColonne;
    private int winLength = 3; // 3 = TTT ; 5 = Gomoku
    public int getWinLength() { return winLength; }


    public Cell[][] toArray() { return board; }



    public Board(int sizeligne, int sizecolonne) {
        this.sizeLigne = sizeligne;
        this.sizeColonne = sizecolonne;
        initBoard();

    }


    public void setWinLength(int k) {
        if (k < 3 || k > Math.max(sizeLigne, sizeColonne)) {
            throw new IllegalArgumentException("winLength invalide: " + k);
        }
        this.winLength = k;
    }

    private boolean inBounds(int x, int y) {
        return x >= 0 && x < sizeLigne && y >= 0 && y < sizeColonne;
    }



    public int getSizeLigne() {
        return sizeLigne;
    }
    public int getSizeColonne() {
        return sizeColonne;
    }


    public void initBoard() {
        // (ré)alloue le tableau si nécessaire
        if (board == null || board.length != sizeLigne || board[0].length != sizeColonne) {
            board = new Cell[sizeLigne][sizeColonne];
        }
        for (int i = 0; i < sizeLigne; i++) {
            for (int j = 0; j < sizeColonne; j++) {
                board[i][j] = new Cell();
            }
        }
    }

    public boolean isBoardFull() {
        for (int i = 0; i < sizeLigne; i++) {
            for (int j = 0; j < sizeColonne; j++) {
                if (board[i][j].isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }
    public Cell getCell(int r, int c) {
        return board[r][c];
    }



}
