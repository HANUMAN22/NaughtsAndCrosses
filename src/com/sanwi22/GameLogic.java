package com.sanwi22;

// GameLogic.java (Tracks game state)
import java.awt.Point;
import java.util.HashMap;

class GameLogic {
    HashMap<Point, Character> board = new HashMap<>();

    public boolean makeMove(int x, int y, char player) {
        Point p = new Point(x, y);
        if (!board.containsKey(p)) {
            board.put(p, player);
            return true;
        }
        return false;
    }

    public char get(int x, int y) {
        return board.getOrDefault(new Point(x, y), ' ');
    }

    public boolean isWin(int x, int y, char player) {
        int[][] dirs = {{1,0},{0,1},{1,1},{1,-1}};
        for (int[] d : dirs) {
            int count = 1;
            for (int i = 1; i <= 4; i++) if (get(x + d[0]*i, y + d[1]*i) == player) count++;
            for (int i = 1; i <= 4; i++) if (get(x - d[0]*i, y - d[1]*i) == player) count++;
            if (count >= 5) return true;
        }
        return false;
    }
}

