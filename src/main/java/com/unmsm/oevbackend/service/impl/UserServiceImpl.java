package com.unmsm.oevbackend.service.impl;

import com.unmsm.oevbackend.dto.request.UpdateUserRequestDTO;
import com.unmsm.oevbackend.dto.response.UserResponseDTO;
import com.unmsm.oevbackend.exception.UserNotFoundException;
import com.unmsm.oevbackend.mapper.UserMapper;
import com.unmsm.oevbackend.model.User;
import com.unmsm.oevbackend.repository.IUserRepository;
import com.unmsm.oevbackend.service.interfaces.IUserService;
import com.unmsm.oevbackend.utils.NullPropertiesUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    private final UserMapper userMapper;

    @Override
    public List<UserResponseDTO> findAllUsers() {
        List<User> findAllUsers = userRepository.findAll();
        return userMapper.entityListToDTOList(findAllUsers);
    }

    @Override
    public UserResponseDTO findUserById(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        return userMapper.entityToDTO(user.get());
    }

    @Override
    public UserResponseDTO findUserByEmail(String username) {
        Optional<User> user = userRepository.findUserByEmail(username);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with email " + username + " not found", HttpStatus.NOT_FOUND);
        }
        return userMapper.entityToDTO(user.get());
    }

    @Override
    public UserResponseDTO updateUserById(Long id, UpdateUserRequestDTO updateUserRequestDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User with id " + id + " not found", HttpStatus.NOT_FOUND));

        // Copiar solo propiedades no nulas del DTO a la entidad
        BeanUtils.copyProperties(updateUserRequestDTO, existingUser, NullPropertiesUtil.getNullPropertyNames(updateUserRequestDTO));


        User updatedUser = userRepository.save(existingUser);
        return userMapper.entityToDTO(updatedUser);
    }

    @Override
    public void deleteUserById(Long id) {

        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User with id " + id + " not found", HttpStatus.NOT_FOUND);
        }
        userRepository.deleteById(id);

    }

}
