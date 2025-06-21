package com.unmsm.oevbackend.service.interfaces;

import com.unmsm.oevbackend.dto.request.UpdateUserRequestDTO;
import com.unmsm.oevbackend.dto.response.UserResponseDTO;

import java.util.List;

public interface IUserService {

    List<UserResponseDTO> findAllUsers();

    UserResponseDTO findUserById(Long id);

    UserResponseDTO findUserByEmail(String username);

    UserResponseDTO updateUserById(Long id, UpdateUserRequestDTO updateUserRequestDTO);

    void deleteUserById(Long id);


}
