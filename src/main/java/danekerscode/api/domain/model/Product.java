package danekerscode.api.domain.model;

import danekerscode.api.common.constants.ProductCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
public class Product extends BaseEntity {
    private String name;
    private Float rating;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private ProductCategory category;
    private String imageUrl;
}
