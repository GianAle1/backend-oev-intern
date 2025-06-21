package com.unmsm.oevbackend.mapper;

import com.unmsm.oevbackend.dto.response.UserLessonProgressResponseDTO;
import com.unmsm.oevbackend.model.Lesson;
import com.unmsm.oevbackend.model.User;
import com.unmsm.oevbackend.model.UserLessonProgress;
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
public class UserLessonProgressMapperImpl implements UserLessonProgressMapper {

    @Override
    public UserLessonProgressResponseDTO entityToResponseDTO(UserLessonProgress userLessonProgress) {
        if ( userLessonProgress == null ) {
            return null;
        }

        UserLessonProgressResponseDTO.UserLessonProgressResponseDTOBuilder userLessonProgressResponseDTO = UserLessonProgressResponseDTO.builder();

        userLessonProgressResponseDTO.userId( userLessonProgressUserId( userLessonProgress ) );
        userLessonProgressResponseDTO.lessonId( userLessonProgressLessonId( userLessonProgress ) );
        userLessonProgressResponseDTO.lessonTitle( userLessonProgressLessonTitle( userLessonProgress ) );
        userLessonProgressResponseDTO.lessonVideoKey( userLessonProgressLessonVideoKey( userLessonProgress ) );
        userLessonProgressResponseDTO.duration( userLessonProgressLessonDuration( userLessonProgress ) );
        userLessonProgressResponseDTO.id( userLessonProgress.getId() );
        userLessonProgressResponseDTO.status( userLessonProgress.getStatus() );
        userLessonProgressResponseDTO.completedAt( userLessonProgress.getCompletedAt() );

        return userLessonProgressResponseDTO.build();
    }

    @Override
    public List<UserLessonProgressResponseDTO> entityToResponseDTO(List<UserLessonProgress> userLessonProgresses) {
        if ( userLessonProgresses == null ) {
            return null;
        }

        List<UserLessonProgressResponseDTO> list = new ArrayList<UserLessonProgressResponseDTO>( userLessonProgresses.size() );
        for ( UserLessonProgress userLessonProgress : userLessonProgresses ) {
            list.add( entityToResponseDTO( userLessonProgress ) );
        }

        return list;
    }

    private Long userLessonProgressUserId(UserLessonProgress userLessonProgress) {
        if ( userLessonProgress == null ) {
            return null;
        }
        User user = userLessonProgress.getUser();
        if ( user == null ) {
            return null;
        }
        Long id = user.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private Long userLessonProgressLessonId(UserLessonProgress userLessonProgress) {
        if ( userLessonProgress == null ) {
            return null;
        }
        Lesson lesson = userLessonProgress.getLesson();
        if ( lesson == null ) {
            return null;
        }
        Long id = lesson.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    private String userLessonProgressLessonTitle(UserLessonProgress userLessonProgress) {
        if ( userLessonProgress == null ) {
            return null;
        }
        Lesson lesson = userLessonProgress.getLesson();
        if ( lesson == null ) {
            return null;
        }
        String title = lesson.getTitle();
        if ( title == null ) {
            return null;
        }
        return title;
    }

    private String userLessonProgressLessonVideoKey(UserLessonProgress userLessonProgress) {
        if ( userLessonProgress == null ) {
            return null;
        }
        Lesson lesson = userLessonProgress.getLesson();
        if ( lesson == null ) {
            return null;
        }
        String videoKey = lesson.getVideoKey();
        if ( videoKey == null ) {
            return null;
        }
        return videoKey;
    }

    private Integer userLessonProgressLessonDuration(UserLessonProgress userLessonProgress) {
        if ( userLessonProgress == null ) {
            return null;
        }
        Lesson lesson = userLessonProgress.getLesson();
        if ( lesson == null ) {
            return null;
        }
        Integer duration = lesson.getDuration();
        if ( duration == null ) {
            return null;
        }
        return duration;
    }
}
