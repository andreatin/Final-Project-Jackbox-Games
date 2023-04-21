package tictactoe;

import java.util.Scanner;

//driver class for the tictactoe game. This is the class that is called from the main menu
public class TicTacToeDriver {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Work In Progress.");

        TicTacToe game = new TicTacToe();

        System.out.println("Welcome to Tic Tac Toe\n");

        System.out.println("Will you be playing against the computer or an opponent? 1 for Computer.");
        int choice = input.nextInt();
        if (choice == 1) {
            game.enableComputer();
        }

        System.out.println("Would you like to use the default x's and o's? 1 for Yes.");
        choice = input.nextInt();
        if (choice != 1) {
            System.out.println("Enter a character to use for Player 1: ");
            char player1 = input.next().charAt(0);

            System.out.println("Enter a character to use for Player 2: ");
            char player2 = input.next().charAt(0);

            game.setPlayerChars(player1, player2);
        }

        game.playTTT();
    }
}