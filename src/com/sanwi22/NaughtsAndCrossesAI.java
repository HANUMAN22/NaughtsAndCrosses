package com.sanwi22;

import java.awt.*;
import java.awt.event.*;

public class NaughtsAndCrossesAI extends Frame implements MouseListener, ActionListener {
    private final int PADDING = 60;
    private final int GRID_SIZE = 3;
    private final char[][] board = new char[GRID_SIZE][GRID_SIZE];
    private boolean xTurn = true; // Human is 'X'
    private boolean gameOver = false;

    private final Button restartButton = new Button("Restart");

    public NaughtsAndCrossesAI() {
        setTitle("Naughts and Crosses - Human vs AI");
        setSize(600, 650); // Extra space for button
        setBackground(new Color(250, 250, 245));
        setLayout(null);

        restartButton.setBounds(250, 580, 100, 30);
        restartButton.setFont(new Font("Arial", Font.BOLD, 14));
        restartButton.addActionListener(this);
        add(restartButton);

        addMouseListener(this);

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
                System.exit(0);
            }
        });

        setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int size = Math.min(getWidth(), getHeight() - 70) - 2 * PADDING;
        int cellSize = size / GRID_SIZE;

        // Draw grid lines
        g2.setColor(Color.DARK_GRAY);
        g2.setStroke(new BasicStroke(4));
        for (int i = 1; i < GRID_SIZE; i++) {
            g2.drawLine(PADDING + i * cellSize, PADDING, PADDING + i * cellSize, PADDING + size);
            g2.drawLine(PADDING, PADDING + i * cellSize, PADDING + size, PADDING + i * cellSize);
        }

        // Draw pieces
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                int x = PADDING + col * cellSize;
                int y = PADDING + row * cellSize;
                char piece = board[row][col];

                if (piece == 'X') {
                    g2.setColor(new Color(0, 102, 204));
                    g2.setStroke(new BasicStroke(6));
                    g2.drawLine(x + 15, y + 15, x + cellSize - 15, y + cellSize - 15);
                    g2.drawLine(x + cellSize - 15, y + 15, x + 15, y + cellSize - 15);
                } else if (piece == 'O') {
                    g2.setColor(new Color(200, 50, 50));
                    g2.setStroke(new BasicStroke(6));
                    g2.drawOval(x + 15, y + 15, cellSize - 30, cellSize - 30);
                }
            }
        }

        // Draw game over message
        if (gameOver) {
            g2.setFont(new Font("Arial", Font.BOLD, 32));
            g2.setColor(new Color(20, 120, 20));
            String msg = checkWin('X') ? "You win!" :
                    checkWin('O') ? "AI wins!" : "It's a Draw!";
            FontMetrics fm = g2.getFontMetrics();
            int msgWidth = fm.stringWidth(msg);
            g2.drawString(msg, (getWidth() - msgWidth) / 2, getHeight() - 80);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (gameOver || !xTurn) return;

        int size = Math.min(getWidth(), getHeight() - 70) - 2 * PADDING;
        int cellSize = size / GRID_SIZE;
        int x = e.getX() - PADDING;
        int y = e.getY() - PADDING;

        if (x < 0 || y < 0 || x >= size || y >= size) return;

        int col = x / cellSize;
        int row = y / cellSize;

        if (board[row][col] == '\0') {
            board[row][col] = 'X';
            xTurn = false;

            if (checkWin('X') || isBoardFull()) {
                gameOver = true;
                repaint();
                return;
            }

            // Let AI make a move
            aiMove();

            if (checkWin('O') || isBoardFull()) {
                gameOver = true;
            }

            xTurn = true;
            repaint();
        }
    }

    private void aiMove() {
        int[] bestMove = minimax(board, true);
        board[bestMove[1]][bestMove[2]] = 'O';
    }

    private int[] minimax(char[][] boardState, boolean isAI) {
        if (checkWin('O')) return new int[]{1, -1, -1};
        if (checkWin('X')) return new int[]{-1, -1, -1};
        if (isBoardFull()) return new int[]{0, -1, -1};

        int bestScore = isAI ? Integer.MIN_VALUE : Integer.MAX_VALUE;
        int bestRow = -1, bestCol = -1;

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (boardState[row][col] == '\0') {
                    boardState[row][col] = isAI ? 'O' : 'X';
                    int score = minimax(boardState, !isAI)[0];
                    boardState[row][col] = '\0';

                    if ((isAI && score > bestScore) || (!isAI && score < bestScore)) {
                        bestScore = score;
                        bestRow = row;
                        bestCol = col;
                    }
                }
            }
        }

        return new int[]{bestScore, bestRow, bestCol};
    }

    private boolean checkWin(char player) {
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
                if (board[row][col] == '\0') return false;
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Restart button logic
        for (int row = 0; row < GRID_SIZE; row++)
            for (int col = 0; col < GRID_SIZE; col++)
                board[row][col] = '\0';

        gameOver = false;
        xTurn = true;
        repaint();
    }

    // Unused mouse methods
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}

    public static void main(String[] args) {
        new NaughtsAndCrossesAI();
    }
}
