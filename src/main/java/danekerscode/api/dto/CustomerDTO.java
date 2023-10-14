package danekerscode.api.dto;

import danekerscode.api.constants.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;

public record CustomerDTO(
        @NonNull @NotBlank String name,
        @NonNull @NotBlank String surname,
        @Email String email,
        @NonNull Gender gender
) {
}
