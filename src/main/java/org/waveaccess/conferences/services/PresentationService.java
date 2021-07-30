package org.waveaccess.conferences.services;

import org.waveaccess.conferences.dto.FullRoomDto;
import org.waveaccess.conferences.dto.FullScheduleDto;
import org.waveaccess.conferences.dto.SimplePresentationDto;

import java.util.List;

public interface PresentationService {

    List<FullRoomDto> getAllByRooms();

    void update(SimplePresentationDto presentationDto) throws Exception;

    void create(SimplePresentationDto presentationDto);

    List<FullScheduleDto> getByUserId(Long id);

    void deleteById(Long presentationId);
}
