package com.unmsm.oevbackend.repository;

import com.unmsm.oevbackend.model.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IConferenceRepository extends JpaRepository<Conference, Long> {

    @Query("select c from Conference c where c.user.id = ?1")
    List<Conference> findConferencesPublishedByUserId(Long userId);

}
