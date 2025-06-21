package com.unmsm.oevbackend.controller;

import com.unmsm.oevbackend.dto.response.UserLessonProgressResponseDTO;
import com.unmsm.oevbackend.service.interfaces.IUserLessonProgressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-lesson-progress")
@RequiredArgsConstructor
public class UserLessonProgressRestController {

    private final IUserLessonProgressService userLessonProgressService;


    @GetMapping("/user/{userId}/course/{courseId}")
    public ResponseEntity<List<UserLessonProgressResponseDTO>> findUserLessonProgressesByUserIdAndCourseId(
            @PathVariable Long userId,
            @PathVariable Long courseId) {
        List<UserLessonProgressResponseDTO> response = userLessonProgressService.findUserLessonProgressesByUserIdAndCourseId(userId, courseId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/mark-completed/user/{userId}/lesson/{lessonId}")
    public ResponseEntity<Void> markLessonAsCompleted(@PathVariable Long userId, @PathVariable Long lessonId) {
        userLessonProgressService.markLessonAsCompleted(userId, lessonId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/mark-not-completed/user/{userId}/lesson/{lessonId}")
    public ResponseEntity<Void> markLessonAsNotCompleted(@PathVariable Long userId, @PathVariable Long lessonId) {
        userLessonProgressService.markLessonAsNotCompleted(userId, lessonId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
