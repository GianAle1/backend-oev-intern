package com.unmsm.oevbackend.repository;

import com.unmsm.oevbackend.model.UserLessonProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserLessonProgressRepository extends JpaRepository<UserLessonProgress, Long> {


    @Query("select u from UserLessonProgress u where u.user.id = ?1 and u.lesson.id = ?2")
    Optional<UserLessonProgress> findByUserIdAndLessonId(Long userId, Long lessonId);


    @Query("select (count(u) > 0) from UserLessonProgress u where u.user.id = ?1 and u.lesson.id = ?2 and u.status = 'COMPLETED'")
    boolean isLessonCompletedByUserIdAndLessonId(Long userId, Long lessonId);


    @Query("select u from UserLessonProgress u where u.user.id = ?1 and u.lesson.course.id = ?2")
    List<UserLessonProgress> findUserLessonProgressesByUserIdAndCourseId(Long userId, Long courseId);
}
