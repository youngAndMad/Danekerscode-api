package danekerscode.api.mapper;

import danekerscode.api.model.User;
import org.mapstruct.*;

@Mapper(
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface UserMapper {

    @Mapping(target = "otp", ignore = true)
    @Mapping(target = "email", expression = "java(email)")
    @Mapping(target = "emailVerified", expression = "java(false)")
    User toModel(String email, Integer opt);

    void updateKey(String apiKey, @MappingTarget User user);
}
