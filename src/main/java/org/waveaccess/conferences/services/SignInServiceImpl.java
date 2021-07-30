package org.waveaccess.conferences.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.waveaccess.conferences.dto.EmailAndPasswordDto;
import org.waveaccess.conferences.dto.TokensDto;
import org.waveaccess.conferences.models.User;
import org.waveaccess.conferences.repositories.UserRepository;
import org.waveaccess.conferences.security.jwt.utils.JwtUtil;

import java.util.function.Supplier;

@Service
public class SignInServiceImpl implements SignInService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public TokensDto signIn(EmailAndPasswordDto dto) throws Throwable {
        User user = repository.findByEmail(dto.email).orElseThrow((Supplier<Throwable>) () -> new UsernameNotFoundException("User not found"));
        if (passwordEncoder.matches(dto.password, user.hashPassword)) {
            String accessToken = jwtUtil.createAccessTokenFor(user);

            return TokensDto.builder()
                    .accessToken(accessToken)
                    .build();
        }
        else {
            throw new UsernameNotFoundException("Invalid username or password");
        }
    }
}
