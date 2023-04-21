package tictactoe;

import java.util.Random;
import java.util.ArrayList;

//this class holds the logic that the computer uses for playing against a user 
public class Computer {

    // Gets the 'computer's' choice
    public int getComputerSelection(char[][] table) {
        ArrayList<Integer> options = new ArrayList<>();
        Random rng = new Random();
        int availableOption = 0;

        // Loops to find available spaces
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                //Marks available spaces and adds to a list of options
                if (table[i][j] == '-') {
                    options.add(availableOption);
                }
                availableOption++;
            }
        }
        
        return options.get(rng.nextInt(options.size()));
    }
}