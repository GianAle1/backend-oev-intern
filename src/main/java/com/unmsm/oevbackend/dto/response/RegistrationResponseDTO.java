package com.unmsm.oevbackend.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationResponseDTO {


    private Long id;


    private Long userId;


    private Long conferenceId;

    private String status;

    private LocalDateTime registrationDate;

    private LocalDate conferenceDate;

    private String conferenceName;

    private String conferenceImageUrl;

    private String conferenceCategory;

    private Integer conferenceTotalStudents;

    private String conferenceDescription;

    private String creatorName;


}


