// Original source: https://ssaurel.medium.com/creating-a-connect-four-game-in-java-f45356f1d6ba
// I (Blanktourist) modified it to fit my style
package main;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Simple 2-player Connect Four implementation in Java 8
public class ConnectFour {

    // Characters for players (R for Red, Y for Yellow)
    private static final char[] PLAYERS = { 'R', 'Y' };
    // Dimensions for board
    private final int width, height;
    // Grid representing the board
    private final char[][] grid;
    // Variables to track the last move made by a player
    private int lastCol = -1, lastTop = -1;

    public ConnectFour(int width, int height) {
        this.width = width;
        this.height = height;
        this.grid = new char[height][width];

        for (int h = 0; h < height; h++) {
            for (int w = 0; w < width; w++) {
                this.grid[h][w] = '.';
            }
        }
    }

    // Use streams to print the col numbers and board state
    public String toString() {
        return IntStream.range(0, width).mapToObj(Integer::toString).collect(Collectors.joining()) +
                "\n" +
                Arrays.stream(grid).map(String::new).collect(Collectors.joining("\n"));
    }

    // Get string representation of the row containing the last move
    public String getRowForLastMove() {
        return new String(grid[lastTop]);
    }

    // Get string representation of the col containing the last move
    public String getColForLastMove() {
        StringBuilder sb = new StringBuilder(height);

        for (int h = 0; h < height; h++) {
            sb.append(grid[h][lastCol]);
        }

        return sb.toString();
    }

    // Get string representation of the "/" diagonal containing the last move
    public String getSlashDiagOfLastMove() {
        StringBuilder sb = new StringBuilder(height);

        for (int h = 0; h < height; h++) {
            int w = lastCol + lastTop - h;

            if (0 <= w && w < width) {
                sb.append(grid[h][w]);
            }
        }

        return sb.toString();
    }

    // Get string representation of the "\" diagonal containing the last move
    public String getBackslashDiagOfLastMove() {
        StringBuilder sb = new StringBuilder(height);

        for (int h = 0; h < height; h++) {
            int w = lastCol - lastTop + h;

            if (0 <= w && w < width) {
                sb.append(grid[h][w]);
            }
        }

        return sb.toString();
    }

    // Check if a substring is in str
    public static boolean contains(String str, String substring) {
        return str.indexOf(substring) >= 0;
    }

    // Check if last play is a winning play
    public boolean isWinningPlay() {
        if (lastTop == -1 || lastCol == -1) {
            System.err.println("No move has been made yet");
            return false;
        }

        char sym = grid[lastTop][lastCol];
        // Winning streak with the last play symbol
        String streak = String.format("%c%c%c%c", sym, sym, sym, sym);

        // Check if streak is in row, col, slah diag, or backslash diag
        return contains(getRowForLastMove(), streak) ||
                contains(getColForLastMove(), streak) ||
                contains(getSlashDiagOfLastMove(), streak) ||
                contains(getBackslashDiagOfLastMove(), streak);
    }

    // Prompts the user for a column, repeating until a valid choice is made
    public void chooseAndDrop(char symbol, Scanner input) {
        do {
            System.out.println("\nPlayer " + symbol + " turn: ");
            int col = input.nextInt();

            // Check if column is within bounds
            if (!(0 <= col && col < width)) {
                System.out.println("Column must be between 0 and " + (width - 1));
                continue;
            }

            // Try to place the symbol in the first available row of the choosen column
            // NOTE: we iterate starting at height - 1 because this represent the "visual
            // bottom" of the board
            for (int h = height - 1; h >= 0; h--) {
                if (grid[h][col] == '.') {
                    grid[lastTop = h][lastCol = col] = symbol;
                    return;
                }
            }

            // If column is full, ask for a new input
            System.out.println("Column " + col + " is full.");
        } while (true);
    }

    public static void main(String[] args) {

        try (Scanner input = new Scanner(System.in)) {
            int height = 6;
            int width = 8;
            int maxMoves = height * width;
            ConnectFour board = new ConnectFour(width, height);
            System.out.println("Use 0-" + (width - 1) + " to choose a column");
            System.out.println(board);

            // Main game loop is bounded by maxMoves
            // Use for-loop trick to alternate between players
            for (int player = 0; maxMoves-- > 0; player = 1 - player) {

                // symbol for current player
                char currPlayer = PLAYERS[player];

                // Ask player to choose a column
                board.chooseAndDrop(currPlayer, input);

                // Display the board
                System.out.println(board);

                // Check if a player won. If so, end
                if (board.isWinningPlay()) {
                    System.out.println("\nPlayer " + currPlayer + " wins!");
                    return;
                }
            }

            System.out.println("Game over. No winner. Let's play again!");
        }
    }

}