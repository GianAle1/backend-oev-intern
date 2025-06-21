package com.unmsm.oevbackend.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateUserRequestDTO {

    private Long id;

    private String name;

    private String paternalSurname;

    private String maternalSurname;

    private String email;


    private String phone;


}