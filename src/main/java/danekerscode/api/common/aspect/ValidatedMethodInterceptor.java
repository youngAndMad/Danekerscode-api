package danekerscode.api.common.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

import static danekerscode.api.common.validate.BindingValidator.validateRequest;

@Aspect
@Component
@Slf4j
public class ValidatedMethodInterceptor {

    @Before("@annotation(danekerscode.api.common.annotation.ValidatedMethod) && args(.., br)")
    public void validateMethod(BindingResult br) {
        log.info("validating request in interceptor");
        validateRequest(br);
    }

}
