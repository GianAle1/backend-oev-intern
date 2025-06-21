package com.unmsm.oevbackend.dto.request;

import com.unmsm.oevbackend.model.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRegisterRequestDTO {

    private String name;

    private String paternalSurname;

    private String maternalSurname;

    @NotNull
    @Email
    private String email;

    @NotNull
    private String password;

    private String phone;

    @NotNull
    private Role role;


}
