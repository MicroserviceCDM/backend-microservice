package io.github.cdm.authservice.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Builder
@Getter
@Setter
public class UpdateUserInformationRequest {
    private String id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String avatar;

    // Make the constructor public
    public UpdateUserInformationRequest(String id, String name, String email, String phone, String address, String avatar) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.avatar = avatar;
    }

    public UpdateUserInformationRequest() {

    }
}