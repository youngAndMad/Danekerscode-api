package danekerscode.api.mapper;

import danekerscode.api.dto.ProductDTO;
import danekerscode.api.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface ProductMapper {

    @Mapping(target = "id", ignore = true)
    Product toModel(ProductDTO productDTO);

    void update(@MappingTarget Product product,ProductDTO productDTO);
}
