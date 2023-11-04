package danekerscode.api.domain.mapper;

import danekerscode.api.domain.model.User;
import org.mapstruct.*;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface UserMapper {

    @Mapping(target = "email", expression = "java(email)")
    @Mapping(target = "emailVerified", expression = "java(false)")
    @Mapping(target = "otp", expression = "java(otp)")
    User toModel(String email, Integer otp);

    void updateKey(String apiKey, @MappingTarget User user);

}
