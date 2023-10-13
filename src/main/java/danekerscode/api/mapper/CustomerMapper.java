package danekerscode.api.mapper;

import danekerscode.api.dto.CustomerDTO;
import danekerscode.api.model.Customer;
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
