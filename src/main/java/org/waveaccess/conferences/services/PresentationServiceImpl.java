package org.waveaccess.conferences.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.waveaccess.conferences.dto.*;
import org.waveaccess.conferences.errors.exceptions.BadRequestException;
import org.waveaccess.conferences.models.Presentation;
import org.waveaccess.conferences.models.PresentationParticipants;
import org.waveaccess.conferences.models.Schedule;
import org.waveaccess.conferences.models.User;
import org.waveaccess.conferences.repositories.PresentationRepository;
import org.waveaccess.conferences.repositories.ScheduleRepository;
import org.waveaccess.conferences.security.details.UserDetailsImpl;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PresentationServiceImpl implements PresentationService {

    @Autowired
    private PresentationRepository presentationRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Override
    public List<FullRoomDto> getAllByRooms() {
        List<Schedule> schedules = scheduleRepository.findAll();
        HashMap<Long, FullRoomDto> presentations = (HashMap<Long, FullRoomDto>) schedules.stream()
                .collect(Collectors.toMap(x -> x.room.id, y -> FullRoomDto.builder()
                        .id(y.room.id)
                        .code(y.room.code)
                        .presentations(new ArrayList<>())
                        .build(), (x1, x2) -> x1));

        schedules.forEach(x ->
                presentations.get(x.room.id).presentations.add(FullPresentationDto.builder()
                        .id(x.presentation.id)
                        .name(x.presentation.name)
                        .simpleScheduleDto(SimpleScheduleDto.builder()
                                .id(x.id)
                                .date(x.date.toString())
                                .start(x.start.toString())
                                .end(x.end.toString())
                                .build())
                        .build()
                )
        );

        return new ArrayList<>(presentations.values());
    }

    @Override
    public List<FullScheduleDto> getByUserId(Long id) {
        return scheduleRepository.findAllByUserId(id).stream()
                .map(x -> FullScheduleDto.builder()
                        .id(x.id)
                        .date(x.date.toString())
                        .end(x.end.toString())
                        .start(x.start.toString())
                        .simpleRoomDto(SimpleRoomDto.builder()
                                .id(x.room.id)
                                .code(x.room.code)
                                .build())
                        .simplePresentationDto(SimplePresentationDto.builder()
                                .id(x.presentation.id)
                                .name(x.presentation.name)
                                .build())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public void create(SimplePresentationDto presentationDto) {
        Long userId = ((UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getDetails()).getId();
        Set<PresentationParticipants> presenters = new HashSet<>();
        presenters.add(PresentationParticipants.builder()
                .user(User.builder().id(userId).build())
                .build());

        presentationRepository.save(Presentation.builder()
                .name(presentationDto.name)
                .participants(presenters)
                .build()
        );
    }

    @Override
    @Transactional
    public void update(SimplePresentationDto presentationDto) {
        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Presentation presentation;
        if (userDetails.getAuthorities().toArray()[0].toString().equals("LISTENER")) {
            presentation = presentationRepository.findById(presentationDto.id).orElseThrow(() -> new BadRequestException("Presentation not found"));

            if (presentation.participants.stream().anyMatch(x -> x.user.id.intValue() == userDetails.getId())) {
                throw new BadRequestException("User is already listener of this conference");
            }

            presentation.participants.add(PresentationParticipants.builder()
                    .user(User.builder().id(userDetails.getId()).build())
                    .build());

        } else {
            presentation = presentationRepository.findByIdAndUserId(presentationDto.id, userDetails.getId()).orElseThrow(() -> new BadRequestException("Presentation is not found"));
            presentation.name = presentationDto.name;
        }

        presentationRepository.save(presentation);
    }

    @Override
    @Transactional
    public void deleteById(Long presentationId) {
        presentationRepository.deleteById(presentationId);

        //TODO org.h2.jdbc.JdbcSQLIntegrityConstraintViolationException
    }
}
