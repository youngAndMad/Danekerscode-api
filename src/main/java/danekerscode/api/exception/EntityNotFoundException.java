package danekerscode.api.exception;

import danekerscode.api.model.Image;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(Class<?> c, Long id) {
        super("%s with id  %d not found".formatted(c.getSimpleName(), id));
    }
}
