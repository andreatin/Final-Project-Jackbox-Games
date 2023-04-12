package lottery;

import java.util.Scanner;

public class Lottery {
    public static void main(String[] args) {
        BST lottery = new BST();
        int continuePlaying = 1;
        Scanner scan = new Scanner(System.in);
        do {
            lottery.playLottery();
            System.out.println("Do you want to keep playing? Type 1 for yes, any other number for no.");
            continuePlaying = scan.nextInt();
        } while (continuePlaying == 1);
        System.out.println("Thank you for playing the lottery game. The program has exited.");
        scan.close();
    }

}