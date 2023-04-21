package hangman;

import java.util.Scanner;
import java.io.*;
import hangman.Dictionary.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.List;


public class hangman
{

    public static void main(String[] args)
    {
        //initializing a hashtable with the amount of words in the txt file
        MyHashtable wordTable = new MyHashtable(55900);
        
        //fills table with words and corresponding key
        wordTable = fillTable();

        //random generator function that selects a word from the table
        String selectedWord = wordSelecter(wordTable);
        
        //setting up board and actual gameplay method
        gameSetup(selectedWord);
        gamePlay(selectedWord);
    }
 
    //method used to fill the hashtable with words from a txt file 
    public static MyHashtable fillTable() 
    {
        MyHashtable wordTable = new MyHashtable(55900);
        try {
        
            File wordFile = new File("src/hangman/words.txt");
            Scanner myReader;
            myReader = new Scanner(wordFile);
       
            int key = 1; 

            //while loop splits the lines into individual words to be put into the table
            while(myReader.hasNext())
            {
                String words = myReader.nextLine();
                String[] eachWord = words.split(" ");
                for(String individualWord : eachWord)
                {
                    wordTable.put(key, individualWord);
                    key++;
            }
        }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return wordTable;
    }

    //used for randomly selecting a key from the table to use that word for the game
    public static String wordSelecter(MyHashtable table)
    {
        Object wordObject;
        String word;
        Random rand = new Random();
        int selectedKey = rand.nextInt(55899);
        wordObject = table.get(selectedKey);
        word = wordObject.toString();

        return word;
    }

    //sets up the board with the amount of letter spaces 
    public static void gameSetup(String selectedWord)
    {
        for(int i = 0; i<selectedWord.length(); i++)
        {
            System.out.print("_ ");
        }
    }

    //recieves input from the user and adds the players input to a list of chars for further evaluation
    public static boolean getPlayerGuess(String selectedWord, List<Character> playerGuesses) 
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter a letter:");
        System.out.println();
        String letterGuess = keyboard.nextLine();
        playerGuesses.add(letterGuess.charAt(0));
        
        //returns the boolean result of the statement evaluating if the input letter is in the secret word
        return selectedWord.contains(letterGuess);
      }

      //displays if the user input matches a letter in the secret word to be guessed 
    public static boolean printWordState(String selectedWord, List<Character> playerGuesses) 
    {
        int correctCount = 0;
        for (int i = 0; i < selectedWord.length(); i++) 
        {
            if (playerGuesses.contains(selectedWord.charAt(i))) 
            {
              System.out.print(selectedWord.charAt(i));
              correctCount++;
            }
            else 
            {
              System.out.print("_");
            }
        }
        System.out.println();
          
        //returns the boolean value of the result of if the amount of correct letters is equal to the length of the correct word
        return (selectedWord.length() == correctCount);
    }

    //method used to display the hangman when the user guessed incorrectly
    public static void printHangedMan(int wrongCount) 
    {
        System.out.println("");
        System.out.println("_________");
        System.out.println("    |    ");
        
        if (wrongCount >= 1) {
            System.out.println("    O");
        }
          
        if (wrongCount >= 2) {
            System.out.print("   \\ ");
            if (wrongCount >= 3) {
              System.out.println("/");
            }
            else {
              System.out.println("");
            }
          }
          
          if (wrongCount >= 4) {
            System.out.println("    |");
          }
          
          if (wrongCount >= 5) {
            System.out.print("   / ");
            if (wrongCount >= 6) {
              System.out.println("\\");
            }
            else {
              System.out.println("");
            }
          }
          System.out.println("");
          System.out.println("");
        }
    
  
        //play method that runs the other dependent guessing and evaluating methods
    public static void gamePlay(String selectedWord)
    {
        List<Character> playerGuesses = new ArrayList<>();
        int wrongCount = 0;
        Scanner keyboard = new Scanner(System.in);
        while(true) {

            //determines if the user lost and prints the secret word
            if (wrongCount >= 6) {
              System.out.println("You lose!");
              System.out.println("The word was: " + selectedWord);
              printHangedMan(wrongCount);
              break;
            }

            //runs if the guessed letter is not in the secret word 
            System.out.println("");
            if (!getPlayerGuess(selectedWord, playerGuesses)) {
              wrongCount++;
              System.out.println("That letter is not in the word!");
              System.out.print("You have tried these letters: " + playerGuesses);
              System.out.println("");
              
              //prints the empty lines and the letters arlready guessed along with the hangman
              printWordState(selectedWord, playerGuesses);
              printHangedMan(wrongCount);
            }

            //prints when/if the user guesses the word correctly
            if(printWordState(selectedWord, playerGuesses)) 
            {
                System.out.println("");
                System.out.println("");
                System.out.println("You win!");
                System.out.println("Congratulations!!");
                System.out.println("");
                System.out.println("\\\\     //");
                System.out.println(" \\\\   //");
                System.out.println("  \\\\ //");
                System.out.println("   {*}");
                System.out.println("");
                break;
            }
            else
            {
                System.out.println("The letters you have tried are: " + playerGuesses);
            }
        }

            
            
    }

   
  
}