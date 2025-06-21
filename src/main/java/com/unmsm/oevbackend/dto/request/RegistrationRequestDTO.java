package com.unmsm.oevbackend.dto.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationRequestDTO {

    @NotNull
    private Long userId;

    @NotNull
    private Long conferenceId;

}
