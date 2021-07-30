package org.waveaccess.conferences.services;

import org.waveaccess.conferences.dto.FullScheduleDto;
import org.waveaccess.conferences.dto.SimpleScheduleDto;
import org.waveaccess.conferences.errors.exceptions.NoAuthoritiesException;

public interface ScheduleService {

    void deleteById(Long id) throws NoAuthoritiesException;

    void updateById(SimpleScheduleDto scheduleDto) throws Exception;

    void create(FullScheduleDto simpleScheduleDto) throws NoAuthoritiesException;
}
