package lottery;

import java.util.Scanner;

public class Lottery {
    public static void main(String[] args) {
        BST lottery = new BST();
        //Scanner scan = new Scanner(System.in);
        lottery.playLottery();
        System.out.println("Thank you for playing the lottery game.");
        //scan.close();
    }

}