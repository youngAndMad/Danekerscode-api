package danekerscode.api.domain.dto;

public record ApiKeyDetailsDTO(
        String email,
        Boolean isAdmin
) {
    public ApiKeyDetailsDTO(String email) {
        this(
                email,
                false
        );
    }
}
