package com.unmsm.oevbackend.mapper;

import com.unmsm.oevbackend.dto.response.EnrollmentResponseDTO;
import com.unmsm.oevbackend.model.Enrollment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EnrollmentMapper {


    @Mapping(source = "course.id", target = "courseId")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "course.imageUrl", target = "courseImageUrl")
    @Mapping(source = "course.name", target = "courseName")
    @Mapping(expression = "java(enrollment.getCourse().getUser().getName() + ' ' + enrollment.getCourse().getUser().getPaternalSurname())", target = "instructorName")
    @Mapping(source = "course.category", target = "category")
    EnrollmentResponseDTO entityToResponseDTO(Enrollment enrollment);

    List<EnrollmentResponseDTO> entityListToResponseDTOList(List<Enrollment> enrollments);

}
