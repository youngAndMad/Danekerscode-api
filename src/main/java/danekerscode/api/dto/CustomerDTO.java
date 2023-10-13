package danekerscode.api.dto;

import danekerscode.api.constants.Gender;
import jakarta.validation.constraints.Email;

public record CustomerDTO(
        String name,
        String surname,

        @Email String email,
        Gender gender
) {
}
