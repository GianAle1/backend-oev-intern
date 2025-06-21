package com.unmsm.oevbackend.dto.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EnrollmentResponseDTO {

    private Long id;

    private Long userId;

    private Long courseId;

    private String status;

    private Double progress;

    private LocalDateTime enrollmentDate;

    private String courseImageUrl;

    private String courseName;

    private String instructorName;

    private boolean paid;

    private String category;

}
