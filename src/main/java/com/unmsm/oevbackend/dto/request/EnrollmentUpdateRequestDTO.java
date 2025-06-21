package com.unmsm.oevbackend.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollmentUpdateRequestDTO {

    private String status;
    private Double progress;
    private boolean paid;

}
