package org.waveaccess.conferences.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.waveaccess.conferences.models.Presentation;
import org.waveaccess.conferences.models.Schedule;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {


    @Query("FROM Schedule schedule WHERE schedule.date = :date AND ((schedule.start <= :end AND schedule.end >= :end) OR (schedule.start >= :start AND schedule.end <= :end) OR (schedule.start <= :start AND schedule.end >= :start)) AND schedule.room.id = :roomId")
    List<Schedule> findByDateTimeAndRoom(Date date, Date start, Date end, Long roomId);

    @Query("FROM Schedule schedule LEFT JOIN FETCH schedule.presentation.participants participants WHERE participants.id = :userId")
    List<Schedule> findAllByUserId(Long userId);

    @Modifying
    void deleteByPresentationId(Long presentationId);

    Optional<Schedule> findScheduleByIdAndPresentation_Id(Long id, Long presentationId);
}
