package com.unmsm.oevbackend.controller;

import com.unmsm.oevbackend.dto.request.CourseRequestDTO;
import com.unmsm.oevbackend.dto.request.UpdateCourseRequestDTO;
import com.unmsm.oevbackend.dto.response.CourseResponseDTO;
import com.unmsm.oevbackend.service.interfaces.ICourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@RequiredArgsConstructor
public class CourseRestController {

    private final ICourseService courseService;

    @PostMapping("/create/{userId}")
    public ResponseEntity<CourseResponseDTO> createCourse(@PathVariable Long userId, @Valid @RequestBody CourseRequestDTO courseRequestDTO) {
        return ResponseEntity.ok(courseService.createCourse(userId, courseRequestDTO));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<CourseResponseDTO>> findAll() {
        return ResponseEntity.ok(courseService.findAllCourses());
    }

    @GetMapping("/findAllByUserId/{userId}")
    public ResponseEntity<List<CourseResponseDTO>> findAllByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(courseService.findAllCoursesByUserId(userId));
    }

    @GetMapping("/findCourse/{id}")
    public ResponseEntity<CourseResponseDTO> findCourseById(@PathVariable Long id) {
        return ResponseEntity.ok(courseService.findCourseById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCourseById(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<CourseResponseDTO> updateCourseById(@PathVariable Long id, @Valid @RequestBody UpdateCourseRequestDTO courseRequestDTO) {
        return ResponseEntity.ok(courseService.updateCourseById(id, courseRequestDTO));
    }

}
