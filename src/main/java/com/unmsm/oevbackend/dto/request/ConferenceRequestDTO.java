package com.unmsm.oevbackend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConferenceRequestDTO {

    @NotNull
    private String name;

    @NotNull
    private String description;

    private String imageUrl;

    @NotNull
    private String category;

    @NotNull
    private LocalDate date;

}
