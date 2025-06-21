package com.unmsm.oevbackend.mapper;

import com.unmsm.oevbackend.dto.request.LessonRequestDTO;
import com.unmsm.oevbackend.dto.response.LessonResponseDTO;
import com.unmsm.oevbackend.model.Lesson;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface LessonMapper {

    @Mapping(source = "course.id", target = "courseId")
    LessonResponseDTO entityToDTO(Lesson lesson);

    List<LessonResponseDTO> entityListToDTOList(List<Lesson> lessonList);

    Lesson requestDTOToEntity(LessonRequestDTO lessonRequestDTO);

}
