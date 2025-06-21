package com.unmsm.oevbackend.service.interfaces;

import com.unmsm.oevbackend.dto.request.UserLoginRequestDTO;
import com.unmsm.oevbackend.dto.request.UserRegisterRequestDTO;
import com.unmsm.oevbackend.dto.response.AuthUserResponseDTO;
import com.unmsm.oevbackend.dto.response.TokenResponseDTO;
import com.unmsm.oevbackend.dto.response.UserResponseDTO;

public interface IAuthService {

    AuthUserResponseDTO login(UserLoginRequestDTO userLoginRequestDTO);

    UserResponseDTO register(UserRegisterRequestDTO userRequestDTO);
}
