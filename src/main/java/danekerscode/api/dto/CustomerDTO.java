package danekerscode.api.dto;

import danekerscode.api.constants.Gender;

public record CustomerDTO(
        String name, String surname, String email, Gender gender
) {
}
