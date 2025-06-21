package com.unmsm.oevbackend.repository;

import com.unmsm.oevbackend.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILessonRepository extends JpaRepository<Lesson, Long> {

    @Query("select l from Lesson l where l.course.id = ?1")
    List<Lesson> findLessonsByCourseId(Long courseId);
}
