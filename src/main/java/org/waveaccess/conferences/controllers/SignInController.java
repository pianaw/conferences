package org.waveaccess.conferences.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.waveaccess.conferences.dto.EmailAndPasswordDto;
import org.waveaccess.conferences.dto.TokensDto;
import org.waveaccess.conferences.services.SignInService;

import javax.annotation.security.PermitAll;

@RestController
@RequestMapping("/api/users/auth")
public class SignInController {

    @Autowired
    private SignInService service;

    @PostMapping
    @PermitAll
    public ResponseEntity<TokensDto> signIn(@RequestBody EmailAndPasswordDto dto) throws Throwable {
        return ResponseEntity.ok(service.signIn(dto));
    }
}
