package hangman.List;

//this file serves as the interface for the list structures 
//with the desired methods for that class
public interface ListInterface {

    public boolean isEmpty();

    public int size();

    public void add(int index, Object value)
            throws ListIndexOutOfBoundsException;

    public void remove(int index)
            throws ListIndexOutOfBoundsException;

    public void removeAll();

    public Object get(int index)
            throws ListIndexOutOfBoundsException;

    public int find(Object o);
}
