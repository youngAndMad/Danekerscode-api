package danekerscode.api.controller;

import danekerscode.api.annotation.ValidatedMethod;
import danekerscode.api.payload.request.UserRegistrationRequest;
import danekerscode.api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@RequiredArgsConstructor
@RequestMapping("user")
public class UserController {

    private final UserService userService;

    @ValidatedMethod
    @PostMapping
    ResponseEntity<?> register(
            @RequestBody @Valid UserRegistrationRequest req,
            BindingResult br
    ) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(userService.register(req));
    }

}
