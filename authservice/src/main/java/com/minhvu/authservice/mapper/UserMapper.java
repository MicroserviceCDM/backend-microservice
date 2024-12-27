package com.minhvu.authservice.mapper;
import com.minhvu.authservice.dto.UserDto;
import com.minhvu.authservice.entity.User;
public interface UserMapper {
    UserDto toUserDto(User user);
}
