Noughts and Crosses (Tic-Tac-Toe) ❌⭕

A Java-based collection of Noughts and Crosses implementations, featuring an AI opponent powered by the Minimax algorithm. The project explores artificial intelligence, recursive game-tree search, adversarial decision-making and graphical user interface development.

The repository contains three implementations: a traditional two-player game, a human-versus-AI game featuring an unbeatable Minimax opponent, and an experimental infinite-grid variation.

Features

1. Human vs AI (Minimax)

* Unbeatable AI: Implements the Minimax algorithm to evaluate possible game states and select optimal moves.
* Exhaustive Game-Tree Search: Recursively explores possible moves until reaching a terminal game state.
* Optimal Decision-Making: Uses a scoring system to maximise the AI’s chances of winning while minimising the human player’s chances.
* Guaranteed Result: The AI cannot lose when playing correctly, ensuring that every game ends in either an AI victory or a draw.
* Restart Functionality: Allows players to reset the board and start another game.
* Win and Draw Detection: Automatically identifies winning combinations and completed games.

2. Traditional Two-Player Game

* Local multiplayer with alternating turns.
* Mouse-based interaction.
* Automatic win detection across rows, columns and diagonals.
* Draw detection when the board is full.
* Graphical rendering using Java AWT.

3. Infinite Tic-Tac-Toe

* Expandable Board: Uses a coordinate-based board representation rather than a fixed 3×3 array.
* Dynamic Game State: Stores occupied positions using a HashMap.
* Five-in-a-Row Win Condition: Detects five consecutive matching symbols horizontally, vertically or diagonally.
* Minimax-Based AI: Evaluates potential moves within a local search area around the most recent move.
* Custom Grid Rendering: Displays a 20×20 viewport of a larger coordinate-based playing area.

Technologies Used

* Language: Java
* GUI: Java AWT and Swing
* AI Algorithm: Minimax
* Data Structures: 2D arrays, HashMaps and Points
* Core Programming Concepts:
    * Recursive algorithms
    * Game-tree search
    * Adversarial decision-making
    * State-space exploration
    * Data structures
    * Event-driven programming
    * Object-Oriented Programming

Understanding the Minimax Algorithm

The Minimax algorithm is a recursive decision-making algorithm used in two-player games where both players have opposing objectives.

It evaluates possible future game states, assuming that both players make optimal decisions.

The algorithm alternates between two objectives:

* Maximising Player (AI): Attempts to achieve the highest possible evaluation score.
* Minimising Player (Human): Attempts to achieve the lowest possible evaluation score.

Evaluation Function

The AI assigns scores to terminal game states:

Outcome	Score
AI wins	+1
Draw	0
Human wins	-1

The algorithm recursively explores possible moves and propagates their evaluation scores back through the game tree.

The AI selects the move with the highest achievable score, accounting for the opponent’s optimal responses.

Why the AI Cannot Lose

In standard 3×3 Noughts and Crosses, the number of possible game states is sufficiently small to allow exhaustive search.

By evaluating all possible future moves until a terminal state is reached, the AI can determine which moves guarantee the best possible outcome.

Because the algorithm assumes an optimal opponent, it will never choose a move that allows an avoidable defeat.

The result is an AI opponent that either wins or forces a draw.

Infinite Tic-Tac-Toe: An Alternative Approach

The infinite-grid implementation explores a variation of the traditional game.

Instead of restricting the board to nine positions, it uses a coordinate-based system to represent an effectively unbounded playing area.

Board Representation

The board is represented using a HashMap:

HashMap<Point, Character> board = new HashMap<>();

Each occupied coordinate is associated with either an X or an O. Empty positions are not stored, allowing the board to grow dynamically without allocating a large fixed-size array.

Win Detection

The game checks four directions:

* Horizontal
* Vertical
* Diagonal
* Anti-diagonal

A player wins by forming a sequence of five consecutive matching symbols.

AI Search

The infinite-grid AI uses a depth-limited Minimax implementation.

Rather than searching the entire board, it evaluates potential moves within a local region surrounding the most recent move.

This provides an experimental approach to applying adversarial search to a much larger game state.

Unlike the standard 3×3 implementation, this version does not guarantee optimal play because its search depth is limited.

Project Structure

NaughtsAndCrosses/
│
└── src/
    └── com/
        └── sanwi22/
            │
            ├── Main.java
            ├── NaughtsAndCrosses.java
            ├── NaughtsAndCrossesAI.java
            ├── AIPlayer.java
            ├── GameLogic.java
            ├── BoardPanel.java
            └── InfiniteTicTacToe.java

Class Overview

Class	Responsibility
Main	Launches the traditional and AI implementations.
NaughtsAndCrosses	Implements the traditional two-player game.
NaughtsAndCrossesAI	Implements the human-versus-AI game and recursive Minimax algorithm.
AIPlayer	Evaluates potential moves for the infinite-grid variation.
GameLogic	Manages the infinite-grid board and five-in-a-row win detection.
BoardPanel	Handles graphical rendering and mouse interaction for the infinite-grid game.
InfiniteTicTacToe	Initialises the infinite-grid implementation using Swing.

Getting Started

Prerequisites

* Java Development Kit (JDK)
* An IDE such as IntelliJ IDEA, Eclipse or VS Code (optional).

Installation

Clone the repository:

git clone https://github.com/HANUMAN22/NaughtsAndCrosses.git

Navigate into the project directory:

cd NaughtsAndCrosses

Compile the source files:

javac -d out src/com/sanwi22/*.java

Running the Applications

Traditional Two-Player Game:

java -cp out com.sanwi22.NaughtsAndCrosses

Human vs AI:

java -cp out com.sanwi22.NaughtsAndCrossesAI

Infinite Tic-Tac-Toe:

java -cp out com.sanwi22.InfiniteTicTacToe

Each implementation can be launched independently.

Future Improvements

Potential extensions include:

* Implementing Alpha-Beta Pruning to optimise Minimax search.
* Introducing adjustable AI difficulty levels.
* Improving the infinite-grid AI with more sophisticated move evaluation and search strategies.
* Adding move history and undo functionality.
* Improving graphical presentation and animations.
* Introducing additional game variations and board configurations.

Author

Sanwi

Developed as a personal Java programming project exploring artificial intelligence, recursive algorithms, game development and strategic decision-making.

GitHub Repository
