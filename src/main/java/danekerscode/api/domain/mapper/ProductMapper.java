package danekerscode.api.domain.mapper;

import danekerscode.api.domain.dto.ProductDTO;
import danekerscode.api.domain.model.Product;
import org.mapstruct.*;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    Product toModel(ProductDTO productDTO);

    void update(@MappingTarget Product product,ProductDTO productDTO);
}
