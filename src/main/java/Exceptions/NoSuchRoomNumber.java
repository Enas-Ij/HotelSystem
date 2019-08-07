package Exceptions;

public class NoSuchRoomNumber extends RuntimeException {

    public NoSuchRoomNumber(String message) {
        super(message);
    }
}
