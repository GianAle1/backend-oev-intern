package com.unmsm.oevbackend.mapper;

import com.unmsm.oevbackend.dto.response.RegistrationResponseDTO;
import com.unmsm.oevbackend.model.Conference;
import com.unmsm.oevbackend.model.Registration;
import com.unmsm.oevbackend.model.User;
import java.time.LocalDate;
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
public class RegistrationMapperImpl implements RegistrationMapper {

    @Override
    public RegistrationResponseDTO entityToResponseDTO(Registration registration) {
        if ( registration == null ) {
            return null;
        }

        RegistrationResponseDTO.RegistrationResponseDTOBuilder registrationResponseDTO = RegistrationResponseDTO.builder();

        registrationResponseDTO.conferenceId( registrationConferenceId( registration ) );
        registrationResponseDTO.userId( registrationUserId( registration ) );
        registrationResponseDTO.conferenceDate( registrationConferenceDate( registration ) );
        registrationResponseDTO.conferenceName( registrationConferenceName( registration ) );
        registrationResponseDTO.conferenceImageUrl( registrationConferenceImageUrl( registration ) );
        registrationResponseDTO.conferenceCategory( registrationConferenceCategory( registration ) );
        registrationResponseDTO.conferenceTotalStudents( registrationConferenceTotalStudents( registration ) );
        registrationResponseDTO.conferenceDescription( registrationConferenceDescription( registration ) );
        registrationResponseDTO.id( registration.getId() );
        registrationResponseDTO.status( registration.getStatus() );
        registrationResponseDTO.registrationDate( registration.getRegistrationDate() );

        registrationResponseDTO.creatorName( registration.getConference().getUser().getName() + ' ' + registration.getConference().getUser().getPaternalSurname() );

        return registrationResponseDTO.build();
    }

    @Override
    public List<RegistrationResponseDTO> entityListToResponseDTOList(List<Registration> registrations) {
        if ( registrations == null ) {
            return null;
        }

        List<RegistrationResponseDTO> list = new ArrayList<RegistrationResponseDTO>( registrations.size() );
        for ( Registration registration : registrations ) {
            list.add( entityToResponseDTO( registration ) );
        }

        return list;
    }

    private Long registrationConferenceId(Registration registration) {
        if ( registration == null ) {
            return null;
        }
        Conference conference = registration.getConference();
        if ( conference == null ) {
            return null;
        }
        Long id = conference.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long registrationUserId(Registration registration) {
        if ( registration == null ) {
            return null;
        }
        User user = registration.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private LocalDate registrationConferenceDate(Registration registration) {
        if ( registration == null ) {
            return null;
        }
        Conference conference = registration.getConference();
        if ( conference == null ) {
            return null;
        }
        LocalDate date = conference.getDate();
        if ( date == null ) {
            return null;
        }
        return date;
    }

    private String registrationConferenceName(Registration registration) {
        if ( registration == null ) {
            return null;
        }
        Conference conference = registration.getConference();
        if ( conference == null ) {
            return null;
        }
        String name = conference.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String registrationConferenceImageUrl(Registration registration) {
        if ( registration == null ) {
            return null;
        }
        Conference conference = registration.getConference();
        if ( conference == null ) {
            return null;
        }
        String imageUrl = conference.getImageUrl();
        if ( imageUrl == null ) {
            return null;
        }
        return imageUrl;
    }

    private String registrationConferenceCategory(Registration registration) {
        if ( registration == null ) {
            return null;
        }
        Conference conference = registration.getConference();
        if ( conference == null ) {
            return null;
        }
        String category = conference.getCategory();
        if ( category == null ) {
            return null;
        }
        return category;
    }

    private Integer registrationConferenceTotalStudents(Registration registration) {
        if ( registration == null ) {
            return null;
        }
        Conference conference = registration.getConference();
        if ( conference == null ) {
            return null;
        }
        Integer totalStudents = conference.getTotalStudents();
        if ( totalStudents == null ) {
            return null;
        }
        return totalStudents;
    }

    private String registrationConferenceDescription(Registration registration) {
        if ( registration == null ) {
            return null;
        }
        Conference conference = registration.getConference();
        if ( conference == null ) {
            return null;
        }
        String description = conference.getDescription();
        if ( description == null ) {
            return null;
        }
        return description;
    }
}
