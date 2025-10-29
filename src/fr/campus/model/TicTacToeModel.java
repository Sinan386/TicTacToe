package fr.campus.model;

import fr.campus.board.Board;
import fr.campus.player.Player;

import java.util.ArrayList;
import java.util.List;

public class TicTacToeModel {

    private final Board board;

    public TicTacToeModel(Board board) {
        this.board = board;
    }


    public Board getBoard() { return board; }


    public void initBoard() { board.initBoard(); }
    public boolean isBoardFull() { return board.isBoardFull(); }


    public void playMove(int row, int col, Player player) {
        board.getCell(row, col).setSymbol(player.getRepresentation());
    }


    public boolean isValidMove(int r, int c) {
        int maxR = board.getSizeLigne();
        int maxC = board.getSizeColonne();
        if (r < 0 || r >= maxR || c < 0 || c >= maxC) return false;
        return board.getCell(r, c).isEmpty();
    }


    public boolean checkWin(int x, int y) {
        int winLength = board.getWinLength();
        String symbol = board.getCell(x, y).getSymbol();
        if (symbol == null || symbol.isEmpty()) return false;

        // Horizontal
        int count = 1 + countInDirection(x, y, 0, +1, symbol)
                + countInDirection(x, y, 0, -1, symbol);
        if (count >= winLength) return true;

        // Vertical
        count = 1 + countInDirection(x, y, +1, 0, symbol)
                + countInDirection(x, y, -1, 0, symbol);
        if (count >= winLength) return true;

        // Diagonale
        count = 1 + countInDirection(x, y, +1, +1, symbol)
                + countInDirection(x, y, -1, -1, symbol);
        if (count >= winLength) return true;

        // Diagonale
        count = 1 + countInDirection(x, y, +1, -1, symbol)
                + countInDirection(x, y, -1, +1, symbol);
        return count >= winLength;
    }

    public int countInDirection(int x, int y, int dx, int dy, String symbol) {
        int count = 0;
        int newX = x + dx;
        int newY = y + dy;

        int maxR = board.getSizeLigne();
        int maxC = board.getSizeColonne();

        while (newX >= 0 && newX < maxR
                && newY >= 0 && newY < maxC
                && board.getCell(newX, newY).getSymbol().equals(symbol)) {
            count++;
            newX += dx;
            newY += dy;
        }
        return count;
    }


    public List<int[]> getAvailable() {
        List<int[]> available = new ArrayList<>();
        int maxR = board.getSizeLigne();
        int maxC = board.getSizeColonne();
        for (int i = 0; i < maxR; i++) {
            for (int j = 0; j < maxC; j++) {
                if (board.getCell(i, j).isEmpty()) {
                    available.add(new int[]{i, j});
                }
            }
        }
        return available;
    }
}
