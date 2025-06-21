package com.unmsm.oevbackend.dto.request;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UpdateConferenceRequestDTO {

    private String name;
    private String description;
    private String imageUrl;
    private String category;
    private String status;


}