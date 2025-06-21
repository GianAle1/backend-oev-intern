package com.unmsm.oevbackend.service.impl;

import com.unmsm.oevbackend.dto.request.CourseRequestDTO;
import com.unmsm.oevbackend.dto.request.UpdateCourseRequestDTO;
import com.unmsm.oevbackend.dto.response.CourseResponseDTO;
import com.unmsm.oevbackend.exception.AppException;
import com.unmsm.oevbackend.exception.UserNotFoundException;
import com.unmsm.oevbackend.mapper.CourseMapper;
import com.unmsm.oevbackend.mapper.LessonMapper;
import com.unmsm.oevbackend.model.Course;
import com.unmsm.oevbackend.model.Lesson;
import com.unmsm.oevbackend.model.User;
import com.unmsm.oevbackend.model.enums.Role;
import com.unmsm.oevbackend.repository.ICourseRepository;
import com.unmsm.oevbackend.repository.IUserRepository;
import com.unmsm.oevbackend.service.interfaces.ICourseService;
import com.unmsm.oevbackend.service.interfaces.IS3Service;
import com.unmsm.oevbackend.utils.NullPropertiesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements ICourseService {

    private final ICourseRepository courseRepository;

    private final CourseMapper courseMapper;

    private final LessonMapper lessonMapper;

    private final IUserRepository userRepository;

    private final IS3Service s3Service;

    @Override
    public List<CourseResponseDTO> findAllCourses() {
        List<Course> allCourses = courseRepository.findAll();
        return courseMapper.entityListToDTOList(allCourses);
    }

    @Override
    public List<CourseResponseDTO> findAllCoursesByUserId(Long userId) {
        List<Course> allCourses = courseRepository.findCoursesPublishedByUserId(userId);
        return courseMapper.entityListToDTOList(allCourses);
    }


    @Override
    public CourseResponseDTO findCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty()) {
            throw new AppException("Course with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return courseMapper.entityToDTO(course.get());
    }

    @Override
    public CourseResponseDTO updateCourseById(Long id, UpdateCourseRequestDTO updateCourseRequestDTO) {
        Course existingCourse = courseRepository.findById(id)
                .orElseThrow(() -> new AppException("Course with id " + id + " not found", HttpStatus.NOT_FOUND));

        // Copiar solo propiedades no nulas del DTO a la entidad
        BeanUtils.copyProperties(updateCourseRequestDTO, existingCourse, NullPropertiesUtil.getNullPropertyNames(updateCourseRequestDTO));

        existingCourse.setLastUpdate(LocalDateTime.now());

        Course updatedCourse = courseRepository.save(existingCourse);
        return courseMapper.entityToDTO(updatedCourse);
    }


    @Override
    public void deleteCourseById(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isEmpty()) {
            throw new AppException("Course with id " + id + " not found", HttpStatus.NOT_FOUND);
        }

        List<Lesson> lessons = course.get().getLessonList();

        if (lessons != null && !lessons.isEmpty()) {
            lessons.forEach(lesson -> s3Service.deleteFile("oev-mooc-bucket", lesson.getVideoKey()));
        }


        courseRepository.deleteById(id);
    }

    @Override
    public CourseResponseDTO createCourse(Long userId, CourseRequestDTO courseRequestDTO) {
        Optional<User> user = userRepository.findById(userId);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with id " + userId + " not found", HttpStatus.NOT_FOUND);
        }

        User userEntity = user.get();

        if (!userEntity.getRole().equals(Role.INSTRUCTOR)) {
            throw new AppException("User with id " + userId + " is not allowed to create courses", HttpStatus.FORBIDDEN);
        }

        Course course = courseMapper.requestDTOToEntity(courseRequestDTO);
        course.setUser(userEntity);
        course.setCreationDate(LocalDateTime.now());
        course.setTotalStudents(0);
        course.setTotalLessons(0);
        course.setStatus("ACTIVE");
        //TODO: Agregar campos automáticos al crear un curso

        courseRepository.save(course);
        return courseMapper.entityToDTO(course);
    }
}
