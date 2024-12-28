package io.github.cdm.authservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@Data
public class ChangePasswordRequest {
    private String id;
    private String currentPassword;
    private String newPassword;
    private String confirmationPassword;

    public ChangePasswordRequest() {
    }

    public ChangePasswordRequest(String id, String currentPassword, String newPassword, String confirmationPassword) {
        this.id = id;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.confirmationPassword = confirmationPassword;
    }
}
