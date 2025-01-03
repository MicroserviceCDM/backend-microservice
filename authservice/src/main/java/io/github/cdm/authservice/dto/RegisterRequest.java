package io.github.cdm.authservice.dto;

import io.github.cdm.authservice.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

  private String name;
  private String email;
  private String password;
  private Role role;
  private String phone_number;
  private String address;
  private String avatar;
}
