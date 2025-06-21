package com.unmsm.oevbackend.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ConferenceResponseDTO {

    private Long id;

    private String name;
    private String description;
    private String imageUrl;
    private String category;
    private Integer totalStudents;
    private String status;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdate;
    private LocalDate date;


    private Long userId;

    private String creatorName;
}
