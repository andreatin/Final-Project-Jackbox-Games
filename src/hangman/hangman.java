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
        MyHashtable wordTable = new MyHashtable(55900);
        wordTable = fillTable();
        String selectedWord = wordSelecter(wordTable);
        System.out.println(selectedWord);
        gameSetup(selectedWord);
        gamePlay(selectedWord);
    }
 
    public static MyHashtable fillTable() 
    {
        MyHashtable wordTable = new MyHashtable(55900);
        try {
        
            File wordFile = new File("src/hangman/words.txt");
            Scanner myReader;
            myReader = new Scanner(wordFile);
       
            int key = 1; 

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

    public static void gameSetup(String selectedWord)
    {
        List<Character> secretWordList = new ArrayList<Character>();
        
        for(int i = 0; i<selectedWord.length(); i++)
        {
            secretWordList.add(selectedWord.charAt(i));
            System.out.print("_ ");
        }
    }

    public static boolean getPlayerGuess(String selectedWord, List<Character> playerGuesses) 
    {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Please enter a letter:");
        System.out.println();
        String letterGuess = keyboard.nextLine();
        playerGuesses.add(letterGuess.charAt(0));
        
        return selectedWord.contains(letterGuess);
      }

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
          
        return (selectedWord.length() == correctCount);
    }

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
    
  
    public static void gamePlay(String selectedWord)
    {
        List<Character> playerGuesses = new ArrayList<>();
        int wrongCount = 0;
        Scanner keyboard = new Scanner(System.in);
        while(true) {

            if (wrongCount >= 6) {
              System.out.println("You lose!");
              System.out.println("The word was: " + selectedWord);
              printHangedMan(wrongCount);
              break;
            }

            System.out.println("");
            if (!getPlayerGuess(selectedWord, playerGuesses)) {
              wrongCount++;
              System.out.println("That letter is not in the word!");
              System.out.print("You have tried these letters: " + playerGuesses);
              System.out.println("");
              printWordState(selectedWord, playerGuesses);
              printHangedMan(wrongCount);
            }
            
            if(printWordState(selectedWord, playerGuesses)) {
                System.out.println("");
                System.out.println("");
                System.out.println("You win!");
                System.out.println("Congratulations!!");
                break;
            }
        }
            
            
    }

   
  
}