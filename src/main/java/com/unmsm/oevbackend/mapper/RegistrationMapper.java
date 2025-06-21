package com.unmsm.oevbackend.mapper;

import com.unmsm.oevbackend.dto.response.RegistrationResponseDTO;
import com.unmsm.oevbackend.model.Registration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RegistrationMapper {

    @Mapping(source = "conference.id", target = "conferenceId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "conference.date", target = "conferenceDate")
    @Mapping(source = "conference.name", target = "conferenceName")
    @Mapping(source = "conference.imageUrl", target = "conferenceImageUrl")
    @Mapping(source = "conference.category", target = "conferenceCategory")
    @Mapping(source = "conference.totalStudents", target = "conferenceTotalStudents")
    @Mapping(source = "conference.description", target = "conferenceDescription")
    @Mapping(expression = "java(registration.getConference().getUser().getName() + ' ' + registration.getConference().getUser().getPaternalSurname())", target = "creatorName")
    RegistrationResponseDTO entityToResponseDTO(Registration registration);

    List<RegistrationResponseDTO> entityListToResponseDTOList(List<Registration> registrations);

}
