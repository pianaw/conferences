package org.waveaccess.conferences.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.waveaccess.conferences.dto.*;
import org.waveaccess.conferences.errors.exceptions.BadRequestException;
import org.waveaccess.conferences.errors.exceptions.NoAuthoritiesException;
import org.waveaccess.conferences.models.Presentation;
import org.waveaccess.conferences.models.Room;
import org.waveaccess.conferences.models.Schedule;
import org.waveaccess.conferences.models.User;
import org.waveaccess.conferences.repositories.PresentationRepository;
import org.waveaccess.conferences.repositories.ScheduleRepository;
import org.waveaccess.conferences.security.details.UserDetailsImpl;

import javax.transaction.Transactional;
import java.sql.Date;
import java.sql.Time;
import java.util.*;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private PresentationRepository presentationRepository;


    @Override
    public void create(FullScheduleDto scheduleDto) throws NoAuthoritiesException {
        Long userId = ((UserDetailsImpl)SecurityContextHolder.getContext().getAuthentication().getDetails()).getId();
        Set<User> presenters = new HashSet<>();
        presenters.add(User.builder()
                .id(userId)
                .build());

        if (scheduleRepository.findByDateTimeAndRoom(Date.valueOf(scheduleDto.date),
                Time.valueOf(scheduleDto.start),
                Time.valueOf(scheduleDto.end),
                scheduleDto.getSimpleRoomDto().id).isPresent()) {
            throw new BadRequestException("This time is taken");
        } //TODO TRIGGER

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        try {
            Schedule schedule = scheduleRepository.save(Schedule.builder()
                    .date(Date.valueOf(scheduleDto.date))
                    .start(Time.valueOf(scheduleDto.start))
                    .end(Time.valueOf(scheduleDto.end))
                    .room(Room.builder()
                            .id(scheduleDto.simpleRoomDto.id)
                            .build())
                    .presentation(Presentation.builder()
                            .id(scheduleDto.simplePresentationDto.id)
                            .participants(presenters)
                            .build())
                    .build());
        }
        catch (Exception e) {
            throw new BadRequestException("Presentation or room is not found");
        }
    }

    @Override
    @Transactional
    public void updateById(SimpleScheduleDto scheduleDto) throws NoAuthoritiesException {
        Schedule schedule = scheduleRepository.findScheduleByIdAndPresentation_Id(scheduleDto.presentationId, scheduleDto.id).orElseThrow(() -> new BadRequestException("Schedule is not found"));
        schedule.date = Date.valueOf(scheduleDto.date);
        schedule.start = Time.valueOf(scheduleDto.start);
        schedule.end = Time.valueOf(scheduleDto.end);

        UserDetailsImpl userDetails = (UserDetailsImpl) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (!scheduleRepository.findByDateTimeAndRoom(schedule.date, schedule.start, schedule.end, schedule.room.id).isPresent()) {
            throw new BadRequestException("This time and room are already taken");
        }

        scheduleRepository.save(schedule);
    }

    @Override
    @Transactional
    public void deleteById(Long presentationId) throws NoAuthoritiesException {
        scheduleRepository.deleteByPresentationId(presentationId);
    }
}
