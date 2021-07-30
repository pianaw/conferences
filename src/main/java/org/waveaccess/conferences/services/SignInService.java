package org.waveaccess.conferences.services;

import org.waveaccess.conferences.dto.EmailAndPasswordDto;
import org.waveaccess.conferences.dto.TokensDto;

public interface SignInService {

    TokensDto signIn(EmailAndPasswordDto user) throws Throwable;
}
