import java.util.Random;
import java.util.Scanner;

public class Battleships {
    public static void main(String[] args) {
        System.out.println("Welcome to this battleship game, time to play!");
        char[][] board = new char[10][10];
        board = fillBoard(board);
        board = playerShipPlacement(board);
        board = iaShipPlacement(board);
        showBoard(board, false);
        letTheBattleBegin(board);
    }

    // Show the board and trigger victory
    private static void showBoard(char[][] board, boolean gameOn) {
        int aiCount = 0;
        int playerCount = 0;
        System.out.println("X 0 1 2 3 4 5 6 7 8 9");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i);
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(" ");
                if(board[i][j] == 'E') {
                    System.out.print('~');
                    aiCount++;
                } else if (board[i][j] == 'S'){
                    System.out.print(board[i][j]);
                    playerCount++;
                } else {
                    System.out.print(board[i][j]);
                }
            }
            System.out.println();
        }
        // Show the remaining ships and trigger victory
        if(gameOn) {
            System.out.println("Remaining player ship: " + playerCount + "\nRemaining AI ship: " + aiCount);
            if(playerCount == 0) {
                System.out.println("The computer win");
                System.exit(0);
            } else if (aiCount == 0) {
                System.out.println("You win!");
                System.exit(0);
            }
        }
    }

    // Fills the board with '~'
    private static char[][] fillBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = '~';
            }
        }
        return board;
    }

    // Ask the player to place his ships
    private static char[][] playerShipPlacement(char[][] board) {
        Scanner input = new Scanner(System.in);
        System.out.print("Time to place your ships: \n");
        for (int i = 0; i < 5; i++) {
            showBoard(board, false);
            System.out.print("Place your ship, " + (5 - i) + " remaining.\nChoose the row: ");
            int x = input.nextInt();
            System.out.print("Choose the column: ");
            int y = input.nextInt();
            if (board[x][y] != '~') {
                System.out.println("You already put a ship there!");
                i--;
            } else {
                board[x][y] = 'S';
            }
        }
        showBoard(board, false);
        return board;
    }

    // Places the ai ships
    private static char[][] iaShipPlacement(char [][] board) {
        for (int i = 0; i < 5; i++) {
            Random r = new Random();
            int x = r.nextInt(9);
            int y = r.nextInt(9);
            if(board[x][y] != '~') {
                i--;
            } else {
                board[x][y] = 'E';
                System.out.println("AI ship deployed.");
            }
        }
        return board;
    }

    private static void letTheBattleBegin(char[][] board) {
        Scanner input = new Scanner(System.in);
        Random r = new Random();
        int x, y;
        System.out.println("The time has come for you to battleship, FOR YOUR LIFE!");
        while(true) {
            System.out.print("Player, you play first: \nChoose the row: ");
            x = input.nextInt();
            System.out.print("Choose the column");
            y = input.nextInt();
            if(board[x][y] == '~' || board[x][y] == '@') {
                board[x][y] = '@';
                System.out.println("You missed");
            } else if (board[x][y] == 'S') {
                board[x][y] = 'X';
                System.out.println("THAT WAS YOUR SHIP");
            } else {
                board[x][y] = '#';
                System.out.println("You destroyed one of his ships");
            }
            showBoard(board, true);
            x = r.nextInt(9);
            y = r.nextInt(9);
            System.out.println("The computer is playing: ");
            if(board[x][y] == '~' ||board[x][y] == '@') {
                board[x][y] = '@';
                System.out.println("He missed");
            } else if (board[x][y] == 'S') {
                board[x][y] = 'X';
                System.out.println("HE GOT YOUR SHIP");
            } else {
                board[x][y] = '#';
                System.out.println("He shot himself");
            }
            showBoard(board, true);
        }
    }
}