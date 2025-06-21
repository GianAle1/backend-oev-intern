package com.unmsm.oevbackend.repository;

import com.unmsm.oevbackend.dto.response.UserResponseDTO;
import com.unmsm.oevbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);


}
