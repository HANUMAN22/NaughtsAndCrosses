package com.sanwi22;

// InfiniteTicTacToe.java (Main class)
import java.awt.*;
import javax.swing.*;

public class InfiniteTicTacToe {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Infinite Tic Tac Toe");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 800);
            frame.setLocationRelativeTo(null);

            GameLogic gameLogic = new GameLogic();
            BoardPanel board = new BoardPanel(gameLogic);
            frame.add(board);

            frame.setVisible(true);
        });
    }
}






