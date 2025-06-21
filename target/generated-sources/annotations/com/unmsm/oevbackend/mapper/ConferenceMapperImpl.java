package com.unmsm.oevbackend.mapper;

import com.unmsm.oevbackend.dto.request.ConferenceRequestDTO;
import com.unmsm.oevbackend.dto.response.ConferenceResponseDTO;
import com.unmsm.oevbackend.model.Conference;
import com.unmsm.oevbackend.model.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-02-27T15:38:17-0500",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 21 (Oracle Corporation)"
)
@Component
public class ConferenceMapperImpl implements ConferenceMapper {

    @Override
    public ConferenceResponseDTO entityToDTO(Conference conference) {
        if ( conference == null ) {
            return null;
        }

        ConferenceResponseDTO.ConferenceResponseDTOBuilder conferenceResponseDTO = ConferenceResponseDTO.builder();

        conferenceResponseDTO.userId( conferenceUserId( conference ) );
        conferenceResponseDTO.id( conference.getId() );
        conferenceResponseDTO.name( conference.getName() );
        conferenceResponseDTO.description( conference.getDescription() );
        conferenceResponseDTO.imageUrl( conference.getImageUrl() );
        conferenceResponseDTO.category( conference.getCategory() );
        conferenceResponseDTO.totalStudents( conference.getTotalStudents() );
        conferenceResponseDTO.status( conference.getStatus() );
        conferenceResponseDTO.creationDate( conference.getCreationDate() );
        conferenceResponseDTO.lastUpdate( conference.getLastUpdate() );
        conferenceResponseDTO.date( conference.getDate() );

        conferenceResponseDTO.creatorName( conference.getUser().getName() + ' ' + conference.getUser().getPaternalSurname() );

        return conferenceResponseDTO.build();
    }

    @Override
    public List<ConferenceResponseDTO> entityListToDTOList(List<Conference> conferences) {
        if ( conferences == null ) {
            return null;
        }

        List<ConferenceResponseDTO> list = new ArrayList<ConferenceResponseDTO>( conferences.size() );
        for ( Conference conference : conferences ) {
            list.add( entityToDTO( conference ) );
        }

        return list;
    }

    @Override
    public Conference requestDTOToEntity(ConferenceRequestDTO conferenceRequestDTO) {
        if ( conferenceRequestDTO == null ) {
            return null;
        }

        Conference.ConferenceBuilder conference = Conference.builder();

        conference.name( conferenceRequestDTO.getName() );
        conference.description( conferenceRequestDTO.getDescription() );
        conference.imageUrl( conferenceRequestDTO.getImageUrl() );
        conference.category( conferenceRequestDTO.getCategory() );
        conference.date( conferenceRequestDTO.getDate() );

        return conference.build();
    }

    private Long conferenceUserId(Conference conference) {
        if ( conference == null ) {
            return null;
        }
        User user = conference.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
