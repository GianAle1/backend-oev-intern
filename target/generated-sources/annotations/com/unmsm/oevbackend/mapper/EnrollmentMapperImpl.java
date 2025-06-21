package com.unmsm.oevbackend.mapper;

import com.unmsm.oevbackend.dto.response.EnrollmentResponseDTO;
import com.unmsm.oevbackend.model.Course;
import com.unmsm.oevbackend.model.Enrollment;
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
public class EnrollmentMapperImpl implements EnrollmentMapper {

    @Override
    public EnrollmentResponseDTO entityToResponseDTO(Enrollment enrollment) {
        if ( enrollment == null ) {
            return null;
        }

        EnrollmentResponseDTO.EnrollmentResponseDTOBuilder enrollmentResponseDTO = EnrollmentResponseDTO.builder();

        enrollmentResponseDTO.courseId( enrollmentCourseId( enrollment ) );
        enrollmentResponseDTO.userId( enrollmentUserId( enrollment ) );
        enrollmentResponseDTO.courseImageUrl( enrollmentCourseImageUrl( enrollment ) );
        enrollmentResponseDTO.courseName( enrollmentCourseName( enrollment ) );
        enrollmentResponseDTO.category( enrollmentCourseCategory( enrollment ) );
        enrollmentResponseDTO.id( enrollment.getId() );
        enrollmentResponseDTO.status( enrollment.getStatus() );
        enrollmentResponseDTO.progress( enrollment.getProgress() );
        enrollmentResponseDTO.enrollmentDate( enrollment.getEnrollmentDate() );
        enrollmentResponseDTO.paid( enrollment.isPaid() );

        enrollmentResponseDTO.instructorName( enrollment.getCourse().getUser().getName() + ' ' + enrollment.getCourse().getUser().getPaternalSurname() );

        return enrollmentResponseDTO.build();
    }

    @Override
    public List<EnrollmentResponseDTO> entityListToResponseDTOList(List<Enrollment> enrollments) {
        if ( enrollments == null ) {
            return null;
        }

        List<EnrollmentResponseDTO> list = new ArrayList<EnrollmentResponseDTO>( enrollments.size() );
        for ( Enrollment enrollment : enrollments ) {
            list.add( entityToResponseDTO( enrollment ) );
        }

        return list;
    }

    private Long enrollmentCourseId(Enrollment enrollment) {
        if ( enrollment == null ) {
            return null;
        }
        Course course = enrollment.getCourse();
        if ( course == null ) {
            return null;
        }
        Long id = course.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long enrollmentUserId(Enrollment enrollment) {
        if ( enrollment == null ) {
            return null;
        }
        User user = enrollment.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String enrollmentCourseImageUrl(Enrollment enrollment) {
        if ( enrollment == null ) {
            return null;
        }
        Course course = enrollment.getCourse();
        if ( course == null ) {
            return null;
        }
        String imageUrl = course.getImageUrl();
        if ( imageUrl == null ) {
            return null;
        }
        return imageUrl;
    }

    private String enrollmentCourseName(Enrollment enrollment) {
        if ( enrollment == null ) {
            return null;
        }
        Course course = enrollment.getCourse();
        if ( course == null ) {
            return null;
        }
        String name = course.getName();
        if ( name == null ) {
            return null;
        }
        return name;
    }

    private String enrollmentCourseCategory(Enrollment enrollment) {
        if ( enrollment == null ) {
            return null;
        }
        Course course = enrollment.getCourse();
        if ( course == null ) {
            return null;
        }
        String category = course.getCategory();
        if ( category == null ) {
            return null;
        }
        return category;
    }
}
