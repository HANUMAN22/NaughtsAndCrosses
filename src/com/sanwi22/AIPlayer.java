package com.sanwi22;

// AIPlayer.java (Minimax AI)
import java.awt.Point;

class AIPlayer {
    public static Point findBestMove(GameLogic logic, int lastX, int lastY) {
        Point bestMove = null;
        int bestScore = Integer.MIN_VALUE;

        for (int dx = -2; dx <= 2; dx++) {
            for (int dy = -2; dy <= 2; dy++) {
                int nx = lastX + dx;
                int ny = lastY + dy;
                if (logic.get(nx, ny) == ' ') {
                    logic.makeMove(nx, ny, 'O');
                    int score = minimax(logic, 0, false, nx, ny);
                    logic.board.remove(new Point(nx, ny));
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new Point(nx, ny);
                    }
                }
            }
        }

        return bestMove;
    }

    private static int minimax(GameLogic logic, int depth, boolean isMax, int lastX, int lastY) {
        if (logic.isWin(lastX, lastY, isMax ? 'X' : 'O')) return isMax ? -10 : 10;
        if (depth >= 2) return 0;

        int best = isMax ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        char player = isMax ? 'O' : 'X';

        for (int dx = -2; dx <= 2; dx++) {
            for (int dy = -2; dy <= 2; dy++) {
                int nx = lastX + dx;
                int ny = lastY + dy;
                if (logic.get(nx, ny) == ' ') {
                    logic.makeMove(nx, ny, player);
                    int score = minimax(logic, depth + 1, !isMax, nx, ny);
                    logic.board.remove(new Point(nx, ny));
                    best = isMax ? Math.max(best, score) : Math.min(best, score);
                }
            }
        }
        return best;
    }
}
