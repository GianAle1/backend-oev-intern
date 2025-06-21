package com.unmsm.oevbackend.dto.response;

import com.unmsm.oevbackend.model.enums.Status;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLessonProgressResponseDTO {


    private Long id;

    private Long userId;

    private Long lessonId;

    private String lessonTitle;

    private String lessonVideoKey;

    private Integer duration;

    private Status status;

    private LocalDateTime completedAt;
}
