package com.unmsm.oevbackend.dto.response;

import com.unmsm.oevbackend.model.enums.Role;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {

    private Long id;

    private String name;

    private String paternalSurname;

    private String maternalSurname;

    private String email;

    private String phone;

    private Role role;


}
