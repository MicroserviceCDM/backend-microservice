package io.github.cdm.authservice.mapper;
import io.github.cdm.authservice.dto.UserDto;
import io.github.cdm.authservice.entity.User;
public interface UserMapper {
    UserDto toUserDto(User user);
}
