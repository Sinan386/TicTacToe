package fr.campus.model;

import fr.campus.board.Board;
import fr.campus.player.Player;
import fr.campus.board.Cell;
import java.util.ArrayList;
import java.util.List;

public class TicTacToeModel {
    private final Board board;
    private final Player[] players;
    private int currentPlayerIndex = 0;

    public TicTacToeModel(Board board, Player[] players) {
        this.board = board;
        this.players = players;
    }

    public Board getBoard() { return board; }
    public int getSize() { return board.board.length; }        // si board exposé
    public int getWinLength() { return board.getWinLength(); }

    public Player getCurrentPlayer() { return players[currentPlayerIndex]; }
    public void switchPlayer() { currentPlayerIndex = 1 - currentPlayerIndex; }

    public boolean playMove(int row, int col, Player p) {
        Cell c = board.getCell(row, col);
        if (!c.isEmpty()) return false;
        c.setSymbol(p.getRepresentation());
        return true;
    }

    public boolean checkWin(int row, int col) {
        String s = board.getCell(row, col).getSymbol();
        if (s.isEmpty()) return false;
        int need = getWinLength();
        return 1 + countInDirection(row, col, 0, 1, s) + countInDirection(row, col, 0,-1, s) >= need ||
                1 + countInDirection(row, col, 1, 0, s) + countInDirection(row, col,-1, 0, s) >= need ||
                1 + countInDirection(row, col, 1, 1, s) + countInDirection(row, col,-1,-1, s) >= need ||
                1 + countInDirection(row, col, 1,-1, s) + countInDirection(row, col,-1, 1, s) >= need;
    }

    private int countInDirection(int r, int c, int dr, int dc, String s) {
        int n = getSize(), k = 0;
        int i = r + dr, j = c + dc;
        while (i >= 0 && i < n && j >= 0 && j < n && board.getCell(i, j).getSymbol().equals(s)) {
            k++; i += dr; j += dc;
        }
        return k;
    }

    public boolean isBoardFull() {
        int n = getSize();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (board.getCell(i, j).isEmpty()) return false;
        return true;
    }

    public List<int[]> getAvailable() {
        int n = getSize();
        List<int[]> free = new ArrayList<>();
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                if (board.getCell(i, j).isEmpty()) free.add(new int[]{i, j});
        return free;
    }
}
