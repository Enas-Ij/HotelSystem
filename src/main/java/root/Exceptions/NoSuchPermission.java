package root.Exceptions;

public class NoSuchPermission extends RuntimeException{

    public NoSuchPermission(String message) {
        super(message);
    }
}
