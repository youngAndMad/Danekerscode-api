package danekerscode.api.mapper;

import danekerscode.api.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface UserMapper {

    @Mapping(target = "email", expression = "java(email)")
    @Mapping(target = "emailVerified", expression = "java(false)")
    User toModel(String email);

    void updateKey(String apiKey, @MappingTarget User user);
}
