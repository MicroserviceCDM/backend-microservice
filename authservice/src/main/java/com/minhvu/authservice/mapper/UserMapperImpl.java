package com.minhvu.authservice.mapper;

import com.minhvu.authservice.dto.UserDto;
import com.minhvu.authservice.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDto toUserDto(User user) {
        if (user == null) {
            return null;
        }
//       List<UserDto.OrderDto> orders = user.getOrders().stream().map(this::toUserDtoOrderDto).toList();
        return new UserDto(user.getId(), user.getEmail(), user.getUsername(), user.getPhone_number(), user.getAddress(), user.getAvatar(), user.getRole());
    }
}
