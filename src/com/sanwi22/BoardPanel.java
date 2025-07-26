package com.sanwi22;

// BoardPanel.java (Handles UI)
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Map;

class BoardPanel extends JPanel implements MouseListener {
    private final int cellSize = 40;
    private final int viewSize = 20; // 20x20 visible grid
    private int offsetX = 0, offsetY = 0;
    private GameLogic logic;

    public BoardPanel(GameLogic logic) {
        this.logic = logic;
        addMouseListener(this);
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 0, getWidth(), getHeight());

        int startX = getWidth()/2 - (viewSize * cellSize)/2;
        int startY = getHeight()/2 - (viewSize * cellSize)/2;

        g2.setColor(Color.GRAY);
        for (int i = 0; i <= viewSize; i++) {
            g2.drawLine(startX, startY + i * cellSize, startX + viewSize * cellSize, startY + i * cellSize);
            g2.drawLine(startX + i * cellSize, startY, startX + i * cellSize, startY + viewSize * cellSize);
        }

        for (Map.Entry<Point, Character> entry : logic.board.entrySet()) {
            int cx = entry.getKey().x - offsetX;
            int cy = entry.getKey().y - offsetY;
            if (cx >= 0 && cy >= 0 && cx < viewSize && cy < viewSize) {
                int px = startX + cx * cellSize;
                int py = startY + cy * cellSize;
                drawSymbol(g2, px, py, entry.getValue());
            }
        }
    }

    private void drawSymbol(Graphics2D g2, int x, int y, char sym) {
        if (sym == 'X') {
            g2.setColor(Color.BLUE);
            g2.setStroke(new BasicStroke(3));
            g2.drawLine(x + 10, y + 10, x + cellSize - 10, y + cellSize - 10);
            g2.drawLine(x + cellSize - 10, y + 10, x + 10, y + cellSize - 10);
        } else if (sym == 'O') {
            g2.setColor(Color.RED);
            g2.setStroke(new BasicStroke(3));
            g2.drawOval(x + 10, y + 10, cellSize - 20, cellSize - 20);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int startX = getWidth()/2 - (viewSize * cellSize)/2;
        int startY = getHeight()/2 - (viewSize * cellSize)/2;
        int cx = (e.getX() - startX) / cellSize + offsetX;
        int cy = (e.getY() - startY) / cellSize + offsetY;

        if (logic.makeMove(cx, cy, 'X')) {
            repaint();
            Point aiMove = AIPlayer.findBestMove(logic, cx, cy);
            if (aiMove != null) {
                logic.makeMove(aiMove.x, aiMove.y, 'O');
                repaint();
            }
        }
    }

    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
}
