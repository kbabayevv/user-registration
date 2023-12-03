package az.ingress.userregistration.config;


import az.ingress.userregistration.dto.request.UserRequest;
import az.ingress.userregistration.dto.response.UserResponse;
import az.ingress.userregistration.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public abstract class UserMapper {

    public final static UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public abstract User mapUserRequestToUserEntity(UserRequest request);

    public abstract UserResponse mapEntityToUserResponse(User user);

}
