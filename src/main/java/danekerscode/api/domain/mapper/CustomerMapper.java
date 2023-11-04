package danekerscode.api.domain.mapper;

import danekerscode.api.domain.dto.CustomerDTO;
import danekerscode.api.domain.model.Customer;
import org.mapstruct.*;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface CustomerMapper {

    @Mapping(target = "id", ignore = true)
    Customer toModel(CustomerDTO dto);

    void update(@MappingTarget Customer customer, CustomerDTO customerDTO);

}
