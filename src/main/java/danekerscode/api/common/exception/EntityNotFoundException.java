package danekerscode.api.common.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Class<?> c, Long id) {
        super("%s with id  %d not found".formatted(c.getSimpleName().toLowerCase(), id));
    }

    public EntityNotFoundException(Class<?> c, String attrName, String attrValue) {
        super("%s by %s %s not found".formatted(c.getSimpleName(), attrName, attrValue));
    }
}
