package com.unmsm.oevbackend.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponseDTO {

    private Long id;
    private String name;
    private String description;
    private String benefits;
    private String targetAudience;
    private String imageUrl;
    private String category;
    private String level;
    private Double price;
    private Integer duration;
    private Integer totalLessons;
    private Integer totalStudents;
    private Integer favorite;
    private String status;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdate;
    private Long userId;
    private String instructorName;
}
