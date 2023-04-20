package lottery;

import java.util.Random;
import java.util.Scanner;

public class BST implements BSTInterface {

    BSTNode root = null;

    public void playLottery() {
        BST lottery = new BST();
        Random random = new Random();
        Scanner scan = new Scanner(System.in);

        System.out.println("How many winning numbers do you want there to be?");
        int numberOfWinnerNumbers = scan.nextInt();

        // Fill the tree with numbers 0 to 99
        for (int i = 0; i < 10000; i++) {
            int randomNumber = random.nextInt(100);
            lottery.put(randomNumber);
        }

        // Delete x number of possible winner numbers
        for (int i = 0; i < numberOfWinnerNumbers; i++) {
            int randomNumber = random.nextInt(100);
            if (lottery.contains(randomNumber))
                lottery.delete(randomNumber);
            else
                lottery.delete(randomNumber - 1);
            System.out.println("Number " + randomNumber + " is a winning number (testing purposes).");
        }

        // Ask the user for the lottery number
        System.out.println("Enter the number you wish the play");
        int lotteryNumber = scan.nextInt();

        // Check if the user won (if the input is not in the tree)
        if (!lottery.contains(lotteryNumber))
            System.out.println("Congratulations, the number " + lotteryNumber + " was a winning number.");
        else
            System.out.println("You did not have luck, try again next time.");

        lottery.makeEmpty();

    }

    // returns true if the BST is empty, false otherwise
    public boolean isEmpty() {

        return root == null;
    }

    // Empties the BST
    public void makeEmpty() {

        root = null;

    }

    // Returns true if the BST contains the integer, false otherwise
    public boolean contains(int i) {
        return recursiveSearch(root, i);
    }

    // Adds an integer to the BST. If the integer is already in the BST, does
    // nothing.
    public void put(int i) {
        root = recursiveInsert(root, i);

    }

    // Removes an integer from the BST. If the integer isn't in the BST, does
    // nothing.
    public void delete(int i) {

        root = recursiveRemove(root, i);

    }

    protected boolean recursiveSearch(BSTNode node, int i) {
        if (node == null) {
            return false;
        } else {
            if (node.number == i) {
                return true;
            } else if (node.number < i) {
                return recursiveSearch(node.left, i);
            } else if (node.number > i) {
                return recursiveSearch(node.right, i);
            }

        }
        return false;
    }

    protected BSTNode recursiveInsert(BSTNode node, int i) {
        if (node == null) {

            return new BSTNode(i);

        } else {
            if (node.number < i) {

                node.left = recursiveInsert(node.left, i);

            } else if (node.number > i) {
                node.right = recursiveInsert(node.right, i);

            }

        }

        return node;

    }

    protected BSTNode recursiveRemove(BSTNode node, int i) {
        if (node != null) {
            if (node.number < i) {
                node.left = recursiveRemove(node.left, i);
            } else if (node.number > i) {
                node.right = recursiveRemove(node.right, i);
            } else if (node.number == i) {
                node = deleteNode(node);
            }
        }

        return node;
    }

    protected BSTNode deleteNode(BSTNode node) {
        if (node.right == null && node.left == null) {
            node = null;
        } else if (node.left != null && node.right == null) {
            node = node.left;
        } else if (node.left == null && node.right != null) {
            node = node.right;
        } else if (node.right != null && node.left != null) {
            node.number = getSmallest(node.right);
            node.right = recursiveRemove(node.right, node.number);
        }
        return node;
    }

    protected int getSmallest(BSTNode node) {
        int smallest = node.number;

        while (node.left != null) {
            smallest = node.left.number;
            node = node.left;
        }
        return smallest;
    }

    // Prints out the tree structure, using indenting to show the different levels
    // of the tree
    public void printTreeStructure() {
        printTree(0, root);
    }

    // Recursive helper for printTreeStructure()
    protected void printTree(int depth, BSTNode node) {
        if (depth == 0)
            System.out.println();
        indent(depth);
        if (node != null) {
            System.out.println(node.number);
            printTree(depth + 1, node.left);
            printTree(depth + 1, node.right);
        } else {
            System.out.println("null");
        }
    }

    // Indents with spaces
    protected void indent(int depth) {
        for (int i = 0; i < depth; i++)
            System.out.print("  "); // Indents two spaces for each unit of depth
    }

}
