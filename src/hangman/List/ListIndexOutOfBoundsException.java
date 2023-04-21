package hangman.List;

//the purpose of this class is to catch an exception that is likely to be thrown and address it
class ListIndexOutOfBoundsException extends IndexOutOfBoundsException {
    public ListIndexOutOfBoundsException(String s) {
        super(s);
    }
}