package com.unmsm.oevbackend.service.impl;

import com.unmsm.oevbackend.config.security.JwtService;
import com.unmsm.oevbackend.dto.request.UserLoginRequestDTO;
import com.unmsm.oevbackend.dto.request.UserRegisterRequestDTO;
import com.unmsm.oevbackend.dto.response.AuthUserResponseDTO;
import com.unmsm.oevbackend.dto.response.TokenResponseDTO;
import com.unmsm.oevbackend.dto.response.UserResponseDTO;
import com.unmsm.oevbackend.exception.AppException;
import com.unmsm.oevbackend.exception.UserNotFoundException;
import com.unmsm.oevbackend.mapper.UserMapper;
import com.unmsm.oevbackend.model.User;
import com.unmsm.oevbackend.repository.IUserRepository;
import com.unmsm.oevbackend.service.interfaces.IAuthService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {


    private final AuthenticationManager authenticationManager;

    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;

    private final UserMapper userMapper;

    private final IUserRepository userRepository;

    public AuthServiceImpl(AuthenticationManager authenticationManager,
                           JwtService jwtService,
                           PasswordEncoder passwordEncoder,
                           UserMapper userMapper,
                           IUserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    @Override
    public AuthUserResponseDTO login(UserLoginRequestDTO userLoginRequestDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginRequestDTO.getEmail(), userLoginRequestDTO.getPassword())
        );

        User user = userRepository.findUserByEmail(userLoginRequestDTO.getEmail())
                .orElseThrow(() -> new UserNotFoundException("User not found", HttpStatus.NOT_FOUND));

        AuthUserResponseDTO authUserResponseDTO = userMapper.entityToAuthUserResponseDTO(user);
//        String token = jwtService.generateToken(user);
        authUserResponseDTO.setToken(jwtService.generateToken(user));
        return authUserResponseDTO;
    }

    @Override
    public UserResponseDTO register(UserRegisterRequestDTO userRequestDTO) {
        if (userRepository.findUserByEmail(userRequestDTO.getEmail()).isPresent()) {
            throw new AppException("User already exists", HttpStatus.CONFLICT);
        }

        User user = userMapper.requestDTOToEntity(userRequestDTO);
        user.setPassword(passwordEncoder.encode(userRequestDTO.getPassword()));
//        user.setRole(Role.STUDENT);
        userRepository.save(user);

        String token = jwtService.generateToken(user);

        return userMapper.entityToDTO(user);

    }
}
