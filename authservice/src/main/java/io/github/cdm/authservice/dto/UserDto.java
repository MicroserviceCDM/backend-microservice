package io.github.cdm.authservice.dto;

import io.github.cdm.authservice.entity.Role;

public record UserDto(String id,
                      String email,
                      String username,
                      String phoneNumber,
                      String address,
                      String avatar,
                      Role role) {
}
