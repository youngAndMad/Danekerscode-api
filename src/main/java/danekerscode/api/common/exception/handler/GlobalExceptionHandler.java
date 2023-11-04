package danekerscode.api.common.exception.handler;

import danekerscode.api.common.exception.AESException;
import danekerscode.api.common.exception.EntityNotFoundException;
import danekerscode.api.common.exception.EmailRegisteredException;
import danekerscode.api.common.exception.InvalidRequestPayloadException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.function.BiFunction;

@Slf4j
@Component
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    ProblemDetail handle(EntityNotFoundException e) {
        return withDetails.apply(e, 404);
    }

    @ExceptionHandler(InvalidRequestPayloadException.class)
    ProblemDetail handle(InvalidRequestPayloadException e) {
        return withDetails.apply(e, 400);
    }

    @ExceptionHandler(EmailRegisteredException.class)
    ProblemDetail handle(EmailRegisteredException e) {
        return withDetails.apply(e, 400);
    }

    @ExceptionHandler(AESException.class)
    ProblemDetail handle(AESException e) {
        return withDetails.apply(e, 500);
    }


    private final BiFunction<RuntimeException, Integer, ProblemDetail>
            withDetails = (e, sc) -> ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(sc), e.getMessage());

}
