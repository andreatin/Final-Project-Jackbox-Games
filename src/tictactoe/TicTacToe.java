package tictactoe;

import java.util.Scanner;

public class TicTacToe {

    private char player1 = 'x';
    private char player2 = 'o';
    private boolean computerEnabler = false;
    
    // Enables computer
    public void enableComputer() {
        computerEnabler = true;
    }

    // Sets the new player symbols
    public void setPlayerChars(char p1, char p2) {
        this.player1 = p1;
        this.player2 = p2;
    }

    // Plays the game
    public void playTTT() {
        Computer quincy = new Computer();
        int totalTurns = 0;
        char[][] game = {
            {'-', '-', '-'},
            {'-', '-', '-'},
            {'-', '-', '-'}
        };
        
        System.out.println(display(game));

        // Gameplay
        while (!winCondition(game)) {
            int row;
            int column;

            // Determines player 1's plot of choice
            do {
                System.out.print("Player " + player1 + ", enter a row number: ");
                row = getPlot();
                System.out.print("Player " + player1 + ", enter a column number: ");
                column = getPlot();

                if (game[row][column] != '-' && (game[row][column] == player1 || game[row][column] == player2))
                    System.out.println("Please enter a valid plot.\n");

                } while(game[row][column] != '-' && (game[row][column] == player1 || game[row][column] == player2));
                game[row][column] = player1;
                totalTurns++;

            System.out.println("\n" + display(game));
            
            // Ends game if player 1 won
            if (winCondition(game))
                break;
            
            //Ends game if no winners
            if (totalTurns == 9) {
                System.out.println("No Winner\nCondition: Tie");
                break;
            }
            
            // Determines whether or not user is playing against a computer
            if (!computerEnabler) {
                // Determines player 2's plot of choice
                do {
                    System.out.print("Player " + player2 + ", enter a row number: ");
                    row = getPlot();
                    System.out.print("Player " + player2 + ", enter a column number: ");
                    column = getPlot();

                    if (game[row][column] != '-' && (game[row][column] == player1 || game[row][column] == player2))
                        System.out.println("Please enter a valid plot.\n");

                } while(game[row][column] != '-' && (game[row][column] == player1 || game[row][column] == player2));
                game[row][column] = player2;
            }
            else {
                // 'Computer's' choice
                switch (quincy.getComputerSelection(game)) {
                    case 0:
                        game[0][0] = player2;
                        break;
                    case 1:
                        game[0][1] = player2;
                        break;
                    case 2:
                        game[0][2] = player2;
                        break;
                    case 3:
                        game[1][0] = player2;
                        break;
                    case 4:
                        game[1][1] = player2;
                        break;
                    case 5:
                        game[1][2] = player2;
                        break;
                    case 6:
                        game[2][0] = player2;
                        break;
                    case 7:
                        game[2][1] = player2;
                        break;
                    default:
                        game[2][2] = player2;
                }
            }
            totalTurns++;

            System.out.println("\n" + display(game));
        }
        System.out.println("Game Over");
    }

    // Gets row and column number
    private int getPlot() {
        Scanner input = new Scanner(System.in);
        int p = input.nextInt() - 1;

        while (p < 0 || p > 2) {
            System.out.print("Please enter a number 1 - 3\n");
            p = input.nextInt() - 1;
        }

        return p;
    }

    // Determines whether the resulting move 
    private boolean winCondition(char[][] table) {

        // Top row
        if (table[0][0] == table[0][1] && table[0][1] == table[0][2] && table[0][1] != '-') {
            System.out.println(winStatement(table[0][1]));
            System.out.println("Condition: Top row");
            return true;
        }
        // Middle row
        else if (table[1][0] == table[1][1] && table[1][1] == table[1][2] && table[1][1] != '-') {
            System.out.println(winStatement(table[1][1]));
            System.out.println("Condition: Middle row");
            return true;
        }
        // Bottom row
        else if (table[2][0] == table[2][1] && table[2][1] == table[2][2] && table[2][1] != '-') {
            System.out.println(winStatement(table[2][1]));
            System.out.println("Condition: Bottom row");
            return true;
        }

        // Left column
        else if (table[0][0] == table[1][0] && table[1][0] == table[2][0] && table[1][0] != '-') {
            System.out.println(winStatement(table[1][0]));
            System.out.println("Condition: Left column");
            return true;
        }
        // Middle column
        else if (table[0][1] == table[1][1] && table[1][1] == table[2][1] && table[1][1] != '-') {
            System.out.println(winStatement(table[1][1]));
            System.out.println("Condition: Middle column");
            return true;
        }
        // Right column
        else if (table[0][2] == table[1][2] && table[1][2] == table[2][2] && table[1][2] != '-') {
            System.out.println(winStatement(table[1][2]));
            System.out.println("Condition: Right column");
            return true;
        }

        // y = -x diagonal
        else if (table[0][0] == table[1][1] && table[1][1] == table[2][2] && table[1][1] != '-') {
            System.out.println(winStatement(table[1][1]));
            System.out.println("Condition: y = -x diagonal");
            return true;
        }
        // y = x diagonal
        else if (table[0][2] == table[1][1] && table[1][1] == table[2][0] && table[1][1] != '-') {
            System.out.println(winStatement(table[1][1]));
            System.out.println("Condition: y = x diagonal");
            return true;
        }

        return false;
    }

    // Display for game
    private String display(char[][] table) {
        return "\t  Column\n\t 1   2   3\n   Row\n" + "    1\t " + 
        table[0][0] + " | " + table[0][1] + " | " + table[0][2] +
        "\n\t---|---|---\n    2    " +
        table[1][0] + " | " + table[1][1] + " | " + table[1][2] +
        "\n\t---|---|---\n    3    " +
        table[2][0] + " | " + table[2][1] + " | " + table[2][2];
    }

    // Win statement
    private String winStatement(char winner) {
        return "Player " + winner + " Wins!";
    }

}