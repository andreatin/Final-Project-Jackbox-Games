package lottery;

interface BSTInterface {

    public boolean isEmpty(); // returns true if the BST is empty, false otherwise

    public void makeEmpty(); // Empties the BST

    public boolean contains(int i); // Returns true if the BST contains the integer, false otherwise

    public void put(int i); // Adds an integer to the BST. If the integer is already in the BST, does
                            // nothing.

    public void delete(int i); // Removes an integer from the BST. If the integer isn't in the BST, does
                               // nothing.

}