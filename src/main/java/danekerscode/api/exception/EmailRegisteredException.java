package danekerscode.api.exception;

public class EmailRegisteredException extends RuntimeException {
    public EmailRegisteredException(String email){
        super("email %s registered yet".formatted(email));
    }
}