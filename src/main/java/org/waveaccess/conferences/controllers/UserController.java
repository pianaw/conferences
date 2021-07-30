package org.waveaccess.conferences.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.waveaccess.conferences.dto.UserForUpdateDto;
import org.waveaccess.conferences.dto.UserDto;
import org.waveaccess.conferences.dto.UserFormDto;
import org.waveaccess.conferences.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    @PreAuthorize("isAnonymous()")
    public ResponseEntity<Void> signUp(@RequestBody UserFormDto user) {
        userService.signUp(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> updateUserRole(@PathVariable Long id, @RequestParam(name = "role", required = true) String role) throws Exception {
        userService.updateUserById(UserForUpdateDto.builder()
                .id(id)
                .role(role)
                .build());
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok().build();
    }


}
