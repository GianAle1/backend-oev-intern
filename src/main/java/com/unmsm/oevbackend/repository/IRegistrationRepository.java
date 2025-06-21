package com.unmsm.oevbackend.repository;

import com.unmsm.oevbackend.model.Registration;
import com.unmsm.oevbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IRegistrationRepository extends JpaRepository<Registration, Long> {

    @Query("select e from Registration e where e.user.id = ?1")
    List<Registration> findRegistrationsByUserId(Long userId);

    @Query("select e from Registration e where e.conference.id = ?1")
    List<Registration> findRegistrationsByConferenceId(Long conferenceId);

    @Query("select (count(e) > 0) from Registration e where e.user.id = ?1 and e.conference.id = ?2")
    boolean existsRegistrationByUserIdAndConferenceId(Long userId, Long conferenceId);

    @Query("select r.user from Registration r where r.conference.id = ?1")
    List<User> findRegisteredUsersByConferenceId(Long conferenceId);
}
