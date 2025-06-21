package com.unmsm.oevbackend.service.impl;

import com.unmsm.oevbackend.dto.request.RegistrationRequestDTO;
import com.unmsm.oevbackend.dto.response.RegistrationResponseDTO;
import com.unmsm.oevbackend.dto.response.UserResponseDTO;
import com.unmsm.oevbackend.exception.AppException;
import com.unmsm.oevbackend.mapper.RegistrationMapper;
import com.unmsm.oevbackend.mapper.UserMapper;
import com.unmsm.oevbackend.model.Conference;
import com.unmsm.oevbackend.model.Registration;
import com.unmsm.oevbackend.model.User;
import com.unmsm.oevbackend.repository.IConferenceRepository;
import com.unmsm.oevbackend.repository.IRegistrationRepository;
import com.unmsm.oevbackend.repository.IUserRepository;
import com.unmsm.oevbackend.service.interfaces.IRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RegistrationServiceImpl implements IRegistrationService {

    private final IRegistrationRepository registrationRepository;

    private final IConferenceRepository conferenceRepository;

    private final IUserRepository userRepository;

    private final RegistrationMapper registrationMapper;

    private final UserMapper userMapper;

    @Override
    public RegistrationResponseDTO createRegistration(RegistrationRequestDTO registrationRequestDTO) {
        Optional<Conference> conference = conferenceRepository.findById(registrationRequestDTO.getConferenceId());
        if (conference.isEmpty()) {
            throw new AppException("Conference with id " + registrationRequestDTO.getConferenceId() + " not found", HttpStatus.NOT_FOUND);
        }
        Optional<User> user = userRepository.findById(registrationRequestDTO.getUserId());
        if (user.isEmpty()) {
            throw new AppException("User with id " + registrationRequestDTO.getUserId() + " not found", HttpStatus.NOT_FOUND);
        }

        if (registrationRepository.existsRegistrationByUserIdAndConferenceId(registrationRequestDTO.getUserId(), registrationRequestDTO.getConferenceId())) {
            throw new AppException("User with id " + registrationRequestDTO.getUserId() + " is already registered in conference with id " + registrationRequestDTO.getConferenceId(), HttpStatus.BAD_REQUEST);
        }

        Conference conferenceEntity = conference.get();
        conferenceEntity.setTotalStudents(conferenceEntity.getTotalStudents() + 1);


        Registration registrationEntity = Registration.builder()
                .conference(conference.get())
                .user(user.get())
                .status("ACTIVE")
                .registrationDate(LocalDateTime.now())
                .build();

        registrationRepository.save(registrationEntity);
        return registrationMapper.entityToResponseDTO(registrationEntity);
    }

    @Override
    public RegistrationResponseDTO findRegistrationById(Long registrationId) {
        Optional<Registration> registration = registrationRepository.findById(registrationId);
        if (registration.isEmpty()) {
            throw new AppException("Registration with id " + registrationId + " not found", HttpStatus.NOT_FOUND);
        }
        return registrationMapper.entityToResponseDTO(registration.get());
    }

    @Override
    public List<RegistrationResponseDTO> findRegistrationsByUserId(Long userId) {
        List<Registration> registrations = registrationRepository.findRegistrationsByUserId(userId);
        return registrationMapper.entityListToResponseDTOList(registrations);

    }

    @Override
    public List<RegistrationResponseDTO> findRegistrationsByConferenceId(Long conferenceId) {
        List<Registration> registrations = registrationRepository.findRegistrationsByConferenceId(conferenceId);
        return registrationMapper.entityListToResponseDTOList(registrations);
    }

    @Override
    public void deleteRegistrationById(Long registrationId) {
        Optional<Registration> registration = registrationRepository.findById(registrationId);
        if (registration.isEmpty()) {
            throw new AppException("Registration with id " + registrationId + " not found", HttpStatus.NOT_FOUND);
        }

        Conference conference = registration.get().getConference();
        conference.setTotalStudents(conference.getTotalStudents() - 1);

        registrationRepository.deleteById(registrationId);
    }

    @Override
    public List<UserResponseDTO> findRegisteredUsersByConferenceId(Long conferenceId) {
        List<User> users = registrationRepository.findRegisteredUsersByConferenceId(conferenceId);
        return userMapper.entityListToDTOList(users);
    }

}
