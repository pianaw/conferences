package org.waveaccess.conferences.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.waveaccess.conferences.dto.FullRoomDto;
import org.waveaccess.conferences.dto.FullScheduleDto;
import org.waveaccess.conferences.dto.SimplePresentationDto;
import org.waveaccess.conferences.security.details.UserDetailsImpl;
import org.waveaccess.conferences.services.PresentationService;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PresentationController {

    @Autowired
    private PresentationService presentationService;

    @GetMapping("/presentations")
    @PermitAll
    public ResponseEntity<List<FullRoomDto>> getSchedule() {
        return ResponseEntity.ok(presentationService.getAllByRooms());
    }

    @PreAuthorize("hasAnyAuthority('LISTENER', 'PRESENTER')")
    @GetMapping("/users/presentations")
    public ResponseEntity<List<FullScheduleDto>> getAllByUserId(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        return ResponseEntity.ok(presentationService.getByUserId(userDetails.getId()));
    }

    @PostMapping("/users/presentations")
    @PreAuthorize("hasAuthority('PRESENTER')")
    public ResponseEntity<Void> create(@RequestBody SimplePresentationDto presentationDto) {
        presentationService.create(presentationDto);
        return ResponseEntity.ok().build();
    }


    @PutMapping("/users/presentations/{presentationId}")
    @PreAuthorize("hasAnyAuthority('PRESENTER', 'LISTENER')")
    public ResponseEntity<Void> update(@PathVariable Long presentationId, @RequestParam(required = false) String name) throws Exception {
        presentationService.update(SimplePresentationDto.builder()
                .id(presentationId)
                .name(name)
                .build());

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/users/presentations/{presentationId}")
    @PreAuthorize("hasAuthority('PRESENTER') && hasPermission(#presentationId, 'Long.class')")
    public ResponseEntity<Void> delete(@PathVariable Long presentationId) {
        presentationService.deleteById(presentationId);

        return ResponseEntity.ok().build();
    }

}

