package com.unmsm.oevbackend.mapper;

import com.unmsm.oevbackend.dto.response.UserLessonProgressResponseDTO;
import com.unmsm.oevbackend.model.UserLessonProgress;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserLessonProgressMapper {


    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "lesson.id", target = "lessonId")
    @Mapping(source = "lesson.title", target = "lessonTitle")
    @Mapping(source = "lesson.videoKey", target = "lessonVideoKey")
    @Mapping(source = "lesson.duration", target = "duration")
    UserLessonProgressResponseDTO entityToResponseDTO(UserLessonProgress userLessonProgress);


    List<UserLessonProgressResponseDTO> entityToResponseDTO(List<UserLessonProgress> userLessonProgresses);

}
