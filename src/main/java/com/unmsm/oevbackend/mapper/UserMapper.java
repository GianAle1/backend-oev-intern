package com.unmsm.oevbackend.mapper;

import com.unmsm.oevbackend.dto.request.UserRegisterRequestDTO;
import com.unmsm.oevbackend.dto.request.UpdateUserRequestDTO;
import com.unmsm.oevbackend.dto.response.AuthUserResponseDTO;
import com.unmsm.oevbackend.dto.response.UserResponseDTO;
import com.unmsm.oevbackend.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User requestDTOToEntity(UserRegisterRequestDTO userRegisterRequestDTO);

    UserResponseDTO entityToDTO(User user);

    List<UserResponseDTO> entityListToDTOList(List<User> users);

    User updateUserDTOToEntity(UpdateUserRequestDTO updateUserRequestDTO);

    AuthUserResponseDTO entityToAuthUserResponseDTO(User user);
}
