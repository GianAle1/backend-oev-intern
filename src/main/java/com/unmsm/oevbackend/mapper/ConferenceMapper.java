package com.unmsm.oevbackend.mapper;

import com.unmsm.oevbackend.dto.request.ConferenceRequestDTO;
import com.unmsm.oevbackend.dto.response.ConferenceResponseDTO;
import com.unmsm.oevbackend.model.Conference;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ConferenceMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(expression = "java(conference.getUser().getName() + ' ' + conference.getUser().getPaternalSurname())",
            target = "creatorName")
    ConferenceResponseDTO entityToDTO(Conference conference);

    List<ConferenceResponseDTO> entityListToDTOList(List<Conference> conferences);

    Conference requestDTOToEntity(ConferenceRequestDTO conferenceRequestDTO);

}
