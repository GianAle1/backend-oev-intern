package com.unmsm.oevbackend.service.interfaces;

import com.unmsm.oevbackend.dto.request.RegistrationRequestDTO;
import com.unmsm.oevbackend.dto.response.RegistrationResponseDTO;
import com.unmsm.oevbackend.dto.response.UserResponseDTO;
import com.unmsm.oevbackend.model.User;

import java.util.List;

public interface IRegistrationService {

    RegistrationResponseDTO createRegistration(RegistrationRequestDTO registrationRequestDTO);

    RegistrationResponseDTO findRegistrationById(Long registrationId);

    List<RegistrationResponseDTO> findRegistrationsByUserId(Long userId);

    List<RegistrationResponseDTO> findRegistrationsByConferenceId(Long conferenceId);

    void deleteRegistrationById(Long registrationId);

    List<UserResponseDTO> findRegisteredUsersByConferenceId(Long conferenceId);
}
