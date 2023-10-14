package danekerscode.api.payload.request;

import jakarta.validation.constraints.Email;

public record MailSendRequest(
        @Email String to,
        Integer otp
) {
}
