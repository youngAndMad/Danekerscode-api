package danekerscode.api.payload.request;

import jakarta.validation.constraints.Email;
import lombok.NonNull;

public record EmailConfirmationRequest(
        @Email
        String email,
        @NonNull
        Integer otp
) {
}
