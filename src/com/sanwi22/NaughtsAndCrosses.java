package com.sanwi22;

import java.awt.*;
import java.awt.event.*;

public class NaughtsAndCrosses extends Frame implements MouseListener {
    private final int PADDING = 50;
    private final int GRID_SIZE = 3;
    private final char[][] board = new char[GRID_SIZE][GRID_SIZE];
    private boolean xTurn = true;
    private boolean gameOver = false;

    public NaughtsAndCrosses() {
        setTitle("Naughts and Crosses");
        setSize(600, 600);
        setVisible(true);
        addMouseListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics g) {
        int size = Math.min(getWidth(), getHeight()) - 2 * PADDING;
        int cellSize = size / GRID_SIZE;

        // Draw grid lines
        for (int i = 1; i < GRID_SIZE; i++) {
            g.drawLine(PADDING + i * cellSize, PADDING, PADDING + i * cellSize, PADDING + size);
            g.drawLine(PADDING, PADDING + i * cellSize, PADDING + size, PADDING + i * cellSize);
        }

        // Draw pieces (X or O)
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                int x = PADDING + col * cellSize;
                int y = PADDING + row * cellSize;
                char piece = board[row][col];

                if (piece == 'X') {
                    g.drawLine(x + 10, y + 10, x + cellSize - 10, y + cellSize - 10);
                    g.drawLine(x + cellSize - 10, y + 10, x + 10, y + cellSize - 10);
                } else if (piece == 'O') {
                    g.drawOval(x + 10, y + 10, cellSize - 20, cellSize - 20);
                }
            }
        }

        // Draw game over message
        if (gameOver) {
            g.setFont(new Font("Arial", Font.BOLD, 30));
            g.setColor(Color.RED);
            String msg = checkWin('X') ? "X wins!" :
                    checkWin('O') ? "O wins!" : "Draw!";
            g.drawString(msg, PADDING, getHeight() - PADDING / 2);
        }
    }

    private boolean checkWin(char player) {
        // Rows and columns
        for (int i = 0; i < GRID_SIZE; i++) {
            if ((board[i][0] == player &&
                    board[i][1] == player &&
                    board[i][2] == player) ||
                    (board[0][i] == player &&
                            board[1][i] == player &&
                            board[2][i] == player)) {
                return true;
            }
        }
        // Diagonals
        return (board[0][0] == player &&
                board[1][1] == player &&
                board[2][2] == player) ||
                (board[0][2] == player &&
                        board[1][1] == player &&
                        board[2][0] == player);
    }

    private boolean isBoardFull() {
        for (int row = 0; row < GRID_SIZE; row++)
            for (int col = 0; col < GRID_SIZE; col++)
                if (board[row][col] == '\0')
                    return false;
        return true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (gameOver) return;

        int size = Math.min(getWidth(), getHeight()) - 2 * PADDING;
        int cellSize = size / GRID_SIZE;
        int x = e.getX() - PADDING;
        int y = e.getY() - PADDING;

        if (x < 0 || y < 0 || x >= size || y >= size) return;

        int col = x / cellSize;
        int row = y / cellSize;

        if (board[row][col] == '\0') {
            board[row][col] = xTurn ? 'X' : 'O';
            xTurn = !xTurn;

            if (checkWin('X') || checkWin('O') || isBoardFull()) {
                gameOver = true;
            }
            repaint();
        }
    }

    // Unused MouseListener methods
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        new NaughtsAndCrosses();
    }
}
