import hangman.hangman;
import lottery.Lottery;
import tictactoe.TicTacToeDriver;
import java.util.Scanner;

import javax.lang.model.util.ElementScanner14;


public class MainMenu {

    public static void main(String[] args)
    {
        Scanner keyboard = new Scanner(System.in);
        int userInput = 0;
        int playAgain;
        System.out.println("Welcome to Jackbox Games!");

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

            System.out.println("Would you like to return to menu to select a game? (enter 1 to continue or 0 to exit)");
            playAgain = keyboard.nextInt();
            
        }while(playAgain == 1);

        System.out.println("Thank you for playing Jackbox games!");
        System.out.println("We hope to see you soon!");
    }
    

}
