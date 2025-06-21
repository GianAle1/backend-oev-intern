package com.unmsm.oevbackend.repository;

import com.unmsm.oevbackend.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ICourseRepository extends JpaRepository<Course, Long> {

    @Query("select c from Course c where c.user.id = ?1")
    List<Course> findCoursesPublishedByUserId(Long userId);


}
