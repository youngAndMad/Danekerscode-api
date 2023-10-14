package danekerscode.api.dto;

import danekerscode.api.constants.ProductCategory;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.NonNull;

import java.math.BigDecimal;

public record ProductDTO(
        @NonNull @NotBlank
        String name,
        @NonNull
        @Min(value = 0)
        Float rating,
        @Min(value = 0)
        BigDecimal price,
        @NonNull
        ProductCategory category
) {
}
