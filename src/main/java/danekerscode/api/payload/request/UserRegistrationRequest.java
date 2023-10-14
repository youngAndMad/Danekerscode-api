package danekerscode.api.payload.request;

import jakarta.validation.constraints.Email;

public record UserRegistrationRequest(
       @Email String email
) {
}
