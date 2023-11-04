package danekerscode.api.domain.payload.request;

import jakarta.validation.constraints.Email;

public record MailSendRequest(
        @Email String to,
        Integer otp
) {
}
