package danekerscode.api.common.validate;

import danekerscode.api.common.exception.InvalidRequestPayloadException;
import lombok.extern.slf4j.Slf4j;
import lombok.experimental.UtilityClass;
import org.springframework.validation.BindingResult;

import java.util.function.Consumer;

/**
 * A utility class for validating and handling Spring MVC binding results.
 */
@Slf4j
@UtilityClass
public class BindingValidator {

    private static final Consumer<BindingResult> returnErrorToClient = br -> {
        var sb = new StringBuilder();

        br.getFieldErrors()
                .forEach(e ->
                        sb
                                .append(e.getDefaultMessage() == null ? e.getCode() : e.getDefaultMessage())
                                .append(".")
                                .append(System.lineSeparator())
                );

        throw new InvalidRequestPayloadException(sb.toString());
    };

    /**
     * Validates a request's binding result and throws an exception with error details if validation fails.
     *
     * @param br The Spring MVC binding result to validate.
     */
    public static void validateRequest(BindingResult br) {
        if (br.hasErrors()) {
            log.error("Invalid request in binding validator");
            returnErrorToClient.accept(br);
        }
    }
}
