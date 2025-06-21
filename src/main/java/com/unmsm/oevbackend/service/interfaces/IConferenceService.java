package com.unmsm.oevbackend.service.interfaces;

import com.unmsm.oevbackend.dto.request.ConferenceRequestDTO;
import com.unmsm.oevbackend.dto.request.UpdateConferenceRequestDTO;
import com.unmsm.oevbackend.dto.response.ConferenceResponseDTO;

import java.util.List;

public interface IConferenceService {

    List<ConferenceResponseDTO> findAllConferences();

    List<ConferenceResponseDTO> findAllConferencesByUserId(Long userId);

    ConferenceResponseDTO findConferenceById(Long id);

    ConferenceResponseDTO updateConferenceById(Long id, UpdateConferenceRequestDTO updateConferenceRequestDTO);

    void deleteConferenceById(Long id);

    ConferenceResponseDTO createConference(Long userId, ConferenceRequestDTO conferenceRequestDTO);

}
