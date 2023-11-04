package danekerscode.api.common.exception;

public class InvalidRequestPayloadException extends RuntimeException {
    public InvalidRequestPayloadException(String msg) {
        super(msg);
    }
}
