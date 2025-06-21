package com.unmsm.oevbackend.dto.request;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateCourseRequestDTO {

    private String name;
    private String description;
    private String benefits;
    private String targetAudience;
    private String category;
    private String level;
    private Double price;
    private String status;
    private String imageUrl;


}