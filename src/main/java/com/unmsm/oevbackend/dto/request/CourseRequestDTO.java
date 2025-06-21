package com.unmsm.oevbackend.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseRequestDTO {

    @NotNull
    private String name;

    @NotNull
    private String description;

    private String benefits;
    private String targetAudience;
    private String imageUrl;
    private String category;
    private String level;
    private Double price;
}
