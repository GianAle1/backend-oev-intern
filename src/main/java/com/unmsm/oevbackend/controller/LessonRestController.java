package com.unmsm.oevbackend.controller;

import com.unmsm.oevbackend.dto.request.LessonRequestDTO;
import com.unmsm.oevbackend.dto.response.LessonResponseDTO;
import com.unmsm.oevbackend.service.interfaces.ILessonService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lesson")
@RequiredArgsConstructor
public class LessonRestController {

    private final ILessonService lessonService;

    @GetMapping("/findLessonsByCourseId/{courseId}")
    public ResponseEntity<List<LessonResponseDTO>> findLessonsByCourseId(@PathVariable Long courseId) {
        return ResponseEntity.ok(lessonService.findLessonsByCourseId(courseId));
    }

    @PostMapping("/create/{courseId}")
    public ResponseEntity<LessonResponseDTO> createLesson(@PathVariable Long courseId, @RequestBody LessonRequestDTO lessonRequestDTO) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(lessonService.createLesson(courseId, lessonRequestDTO));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLessonById(@PathVariable Long id) {
        lessonService.deleteLessonById(id);
        return ResponseEntity.noContent().build();
    }

}
