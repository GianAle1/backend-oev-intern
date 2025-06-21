package com.unmsm.oevbackend.dto.response;

import com.unmsm.oevbackend.model.enums.Status;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LessonResponseDTO {
    private Long id;

    private String title;
    private String videoKey;
    private Integer duration;
    private Integer sequenceOrder;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private Long courseId;

}
