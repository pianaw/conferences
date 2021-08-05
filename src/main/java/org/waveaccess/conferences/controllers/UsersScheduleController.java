package org.waveaccess.conferences.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.waveaccess.conferences.dto.FullScheduleDto;
import org.waveaccess.conferences.dto.SimplePresentationDto;
import org.waveaccess.conferences.dto.SimpleScheduleDto;
import org.waveaccess.conferences.errors.exceptions.NoAuthoritiesException;
import org.waveaccess.conferences.services.ScheduleService;

@RestController
@RequestMapping("/api/users/presentations/{presentationId}/schedule")
public class UsersScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PreAuthorize("hasAuthority('PRESENTER') && hasPermission(#presentationId, 'Long.class')")
    @PostMapping
    public ResponseEntity<Void> create(@PathVariable Long presentationId, @RequestBody FullScheduleDto fullScheduleDto) throws NoAuthoritiesException {
        fullScheduleDto.simplePresentationDto = SimplePresentationDto.builder()
                .id(presentationId)
                .build();
        scheduleService.create(fullScheduleDto);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{scheduleId}")
    @PreAuthorize("hasAuthority('PRESENTER') && hasPermission(#presentationId, 'Long.class')")
    public ResponseEntity<Void> update(@PathVariable Long presentationId, @PathVariable Long scheduleId, @RequestBody SimpleScheduleDto scheduleDto) throws Exception {
        scheduleDto.id = scheduleId;
        scheduleDto.presentationId = presentationId;
        scheduleService.updateById(scheduleDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{scheduleId}")
    @PreAuthorize("hasAuthority('PRESENTER') && hasPermission(#presentationId, 'Long.class')")
    public ResponseEntity<Void> delete(@PathVariable Long presentationId, @PathVariable Long scheduleId) throws NoAuthoritiesException {
        scheduleService.deleteById(scheduleId);
        return ResponseEntity.ok().build();
    }
}
