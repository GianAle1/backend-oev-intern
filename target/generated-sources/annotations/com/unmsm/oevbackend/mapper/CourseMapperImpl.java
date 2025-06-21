package com.unmsm.oevbackend.mapper;

import com.unmsm.oevbackend.dto.request.CourseRequestDTO;
import com.unmsm.oevbackend.dto.response.CourseResponseDTO;
import com.unmsm.oevbackend.model.Course;
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
public class CourseMapperImpl implements CourseMapper {

    @Override
    public CourseResponseDTO entityToDTO(Course course) {
        if ( course == null ) {
            return null;
        }

        CourseResponseDTO.CourseResponseDTOBuilder courseResponseDTO = CourseResponseDTO.builder();

        courseResponseDTO.userId( courseUserId( course ) );
        courseResponseDTO.id( course.getId() );
        courseResponseDTO.name( course.getName() );
        courseResponseDTO.description( course.getDescription() );
        courseResponseDTO.benefits( course.getBenefits() );
        courseResponseDTO.targetAudience( course.getTargetAudience() );
        courseResponseDTO.imageUrl( course.getImageUrl() );
        courseResponseDTO.category( course.getCategory() );
        courseResponseDTO.level( course.getLevel() );
        courseResponseDTO.price( course.getPrice() );
        courseResponseDTO.duration( course.getDuration() );
        courseResponseDTO.totalLessons( course.getTotalLessons() );
        courseResponseDTO.totalStudents( course.getTotalStudents() );
        courseResponseDTO.favorite( course.getFavorite() );
        courseResponseDTO.status( course.getStatus() );
        courseResponseDTO.creationDate( course.getCreationDate() );
        courseResponseDTO.lastUpdate( course.getLastUpdate() );

        courseResponseDTO.instructorName( course.getUser().getName() + ' ' + course.getUser().getPaternalSurname() );

        return courseResponseDTO.build();
    }

    @Override
    public List<CourseResponseDTO> entityListToDTOList(List<Course> courses) {
        if ( courses == null ) {
            return null;
        }

        List<CourseResponseDTO> list = new ArrayList<CourseResponseDTO>( courses.size() );
        for ( Course course : courses ) {
            list.add( entityToDTO( course ) );
        }

        return list;
    }

    @Override
    public Course requestDTOToEntity(CourseRequestDTO courseRequestDTO) {
        if ( courseRequestDTO == null ) {
            return null;
        }

        Course.CourseBuilder course = Course.builder();

        course.name( courseRequestDTO.getName() );
        course.description( courseRequestDTO.getDescription() );
        course.benefits( courseRequestDTO.getBenefits() );
        course.targetAudience( courseRequestDTO.getTargetAudience() );
        course.imageUrl( courseRequestDTO.getImageUrl() );
        course.category( courseRequestDTO.getCategory() );
        course.level( courseRequestDTO.getLevel() );
        course.price( courseRequestDTO.getPrice() );

        return course.build();
    }

    private Long courseUserId(Course course) {
        if ( course == null ) {
            return null;
        }
        User user = course.getUser();
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
