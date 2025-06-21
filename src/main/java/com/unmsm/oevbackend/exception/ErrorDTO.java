package com.unmsm.oevbackend.exception;

import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDTO {

    private String message;

}
