package com.unmsm.oevbackend.repository;

import com.unmsm.oevbackend.model.Enrollment;
import com.unmsm.oevbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEnrollmentRepository extends JpaRepository<Enrollment, Long> {

    @Query("select e from Enrollment e where e.user.id = ?1")
    List<Enrollment> findEnrollmentsByUserId(Long userId);

    @Query("select e from Enrollment e where e.course.id = ?1")
    List<Enrollment> findEnrollmentsByCourseId(Long courseId);

    @Query("select (count(e) > 0) from Enrollment e where e.user.id = ?1 and e.course.id = ?2")
    boolean existsEnrollmentByUserIdAndCourseId(Long userId, Long courseId);

    @Query("select e.user from Enrollment e where e.course.id = ?1")
    List<User> findEnrolledUsersByCourseId(Long courseId);
}
