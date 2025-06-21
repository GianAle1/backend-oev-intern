package com.unmsm.oevbackend.controller;

import com.unmsm.oevbackend.dto.request.RegistrationRequestDTO;
import com.unmsm.oevbackend.dto.response.RegistrationResponseDTO;
import com.unmsm.oevbackend.dto.response.UserResponseDTO;
import com.unmsm.oevbackend.service.interfaces.IRegistrationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/registration")
@RequiredArgsConstructor
public class RegistrationRestController {

    private final IRegistrationService registrationService;

    @PostMapping("/create")
    public ResponseEntity<RegistrationResponseDTO> createRegistration(@Valid @RequestBody RegistrationRequestDTO registrationRequestDTO) {
        RegistrationResponseDTO response = registrationService.createRegistration(registrationRequestDTO);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/findRegistration/{id}")
    public ResponseEntity<RegistrationResponseDTO> findRegistrationById(@PathVariable Long id) {
        RegistrationResponseDTO response = registrationService.findRegistrationById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/findAllByUserId/{userId}")
    public ResponseEntity<List<RegistrationResponseDTO>> findRegistrationsByUserId(@PathVariable Long userId) {
        List<RegistrationResponseDTO> response = registrationService.findRegistrationsByUserId(userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/findAllByConferenceId/{courseId}")
    public ResponseEntity<List<RegistrationResponseDTO>> findRegistrationsByConferenceId(@PathVariable Long courseId) {
        List<RegistrationResponseDTO> response = registrationService.findRegistrationsByConferenceId(courseId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteRegistrationById(@PathVariable Long id) {
        registrationService.deleteRegistrationById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/findRegisteredUsersByConferenceId/{conferenceId}")
    public ResponseEntity<List<UserResponseDTO>> findRegisteredUsersByConferenceId(@PathVariable Long conferenceId) {
        List<UserResponseDTO> response = registrationService.findRegisteredUsersByConferenceId(conferenceId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
