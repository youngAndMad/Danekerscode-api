package danekerscode.api.exception;

public class AESException extends RuntimeException {
    public AESException(Exception e) {
        super(e.getMessage());
    }
}
