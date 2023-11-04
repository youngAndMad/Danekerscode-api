package danekerscode.api.controller;

import danekerscode.api.common.annotation.ValidatedMethod;
import danekerscode.api.domain.payload.request.EmailConfirmationRequest;
import danekerscode.api.domain.payload.request.UserRegistrationRequest;
import danekerscode.api.domain.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @ValidatedMethod
    @PostMapping("confirm-email")
    ResponseEntity<?> confirmEmail(
            @RequestBody @Valid EmailConfirmationRequest req,
            BindingResult br
    ) {
        return ResponseEntity
                .ok(userService.confirmEmail(req));
    }


}
