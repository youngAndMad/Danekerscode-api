package danekerscode.api.exception;

public class InvalidRequestPayloadException extends RuntimeException {
    public InvalidRequestPayloadException(String msg) {
        super(msg);
    }
}
