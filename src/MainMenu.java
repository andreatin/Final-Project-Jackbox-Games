/*
 * COP3530 - SPRING 2023 FINAL PROJECT 
 * April 2023
 * 
 * Created by Andrea Tinsley, Alex Lejárraga, and Brodie Parsons
 * 
 * This program is a Jackbox style game application where there are several mini game options
 * available for the user to play. The user will be shown a main menu where they can select from the list shown.
 * 
 * Currently the game options are 1. tic tac toe, 2.  a lottery guessing game, and 3. hangman
 * 
 */



import hangman.hangman;
import lottery.Lottery;
import tictactoe.TicTacToeDriver;
import java.util.Scanner;

/*this class is used as the main class of the project. Here, the 
//other driver classes are called so that the user can easily 
choose which game they would like to play.*/
public class MainMenu {

    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        int userInput = 0;
        int playAgain;
        System.out.println("Welcome to Jackbox Games!");

        //this switch statement calls the main method of the driver classes of each game, therefore allowing the user 
        //choose their desired game. This loop continues until the user is done playing and exits out
        do{
            System.out.println("Select a game to play: 1 - Tic Tac Toe, 2 - Lottery, 3 - Hangman");
            userInput = keyboard.nextInt();
        
            switch(userInput)
            {
                case 1: 
                    TicTacToeDriver.main(null);
                    break;
                case 2:
                    Lottery.main(null);
                    break;
                case 3:
                    hangman.main(null);
                    break;
                default:
                    System.out.println("Incorrect number entered - that game does not exist");
            }

            System.out.println("Would you like to return to menu to select a game? (enter 1 to continue or any other number to exit)");
            playAgain = keyboard.nextInt();
            
        }while(playAgain == 1);

        System.out.println("Thank you for playing Jackbox games!");
        System.out.println("We hope to see you soon!");
    }
    

}
