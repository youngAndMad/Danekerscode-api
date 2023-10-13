package danekerscode.api.aspect;

import danekerscode.api.validate.BindingValidator;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;

@Aspect
@Component
@Slf4j
public class ValidatedMethodInterceptor {

    @Before("@annotation(danekerscode.api.annotation.ValidatedMethod) && args(.., br)")
    public void validateMethod(BindingResult br) {
        log.info("validating request in interceptor");
        BindingValidator.validateRequest(br);
    }

}
