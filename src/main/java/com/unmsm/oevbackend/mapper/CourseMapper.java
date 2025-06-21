package com.unmsm.oevbackend.mapper;

import com.unmsm.oevbackend.dto.request.CourseRequestDTO;
import com.unmsm.oevbackend.dto.response.CourseResponseDTO;
import com.unmsm.oevbackend.model.Course;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(expression = "java(course.getUser().getName() + ' ' + course.getUser().getPaternalSurname())",
            target = "instructorName")
    CourseResponseDTO entityToDTO(Course course);

    List<CourseResponseDTO> entityListToDTOList(List<Course> courses);

    Course requestDTOToEntity(CourseRequestDTO courseRequestDTO);

}
