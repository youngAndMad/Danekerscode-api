package danekerscode.api.dto;

import danekerscode.api.constants.ProductCategory;

import java.math.BigDecimal;

public record ProductDTO(
        String name, Float rating, BigDecimal price, ProductCategory category
) {
}
