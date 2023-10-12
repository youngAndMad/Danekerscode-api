package danekerscode.api.model;

import danekerscode.api.constants.ProductCategory;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.math.BigDecimal;

@Entity
public class Product extends BaseEntity{
    private String name;
    private Float rating;
    private BigDecimal price;
    @Enumerated(EnumType.STRING)
    private ProductCategory category;
    private String imageUrl;
}
