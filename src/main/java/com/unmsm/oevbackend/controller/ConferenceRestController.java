package com.unmsm.oevbackend.controller;

import com.unmsm.oevbackend.dto.request.ConferenceRequestDTO;
import com.unmsm.oevbackend.dto.request.UpdateConferenceRequestDTO;
import com.unmsm.oevbackend.dto.response.ConferenceResponseDTO;
import com.unmsm.oevbackend.service.interfaces.IConferenceService;
import com.unmsm.oevbackend.service.interfaces.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/conference")
public class ConferenceRestController {

    private final IConferenceService conferenceService;


    @PostMapping("/create/{userId}")
    public ResponseEntity<ConferenceResponseDTO> createConference(@PathVariable Long userId, @Valid @RequestBody ConferenceRequestDTO conferenceRequestDTO) {
        return ResponseEntity.ok(conferenceService.createConference(userId, conferenceRequestDTO));
    }

    @GetMapping("/findAll")
    public ResponseEntity<List<ConferenceResponseDTO>> findAll() {
        return ResponseEntity.ok(conferenceService.findAllConferences());
    }

    @GetMapping("/findAllByUserId/{userId}")
    public ResponseEntity<List<ConferenceResponseDTO>> findAllByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(conferenceService.findAllConferencesByUserId(userId));
    }

    @GetMapping("/findConference/{id}")
    public ResponseEntity<ConferenceResponseDTO> findConferenceById(@PathVariable Long id) {
        return ResponseEntity.ok(conferenceService.findConferenceById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteConferenceById(@PathVariable Long id) {
        conferenceService.deleteConferenceById(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<ConferenceResponseDTO> updateConferenceById(@PathVariable Long id, @Valid @RequestBody UpdateConferenceRequestDTO updateConferenceRequestDTO) {
        return ResponseEntity.ok(conferenceService.updateConferenceById(id, updateConferenceRequestDTO));
    }

}
