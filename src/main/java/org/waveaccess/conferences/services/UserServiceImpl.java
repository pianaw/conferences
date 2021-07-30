package org.waveaccess.conferences.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.waveaccess.conferences.dto.UserForUpdateDto;
import org.waveaccess.conferences.dto.UserDto;
import org.waveaccess.conferences.dto.UserFormDto;
import org.waveaccess.conferences.errors.exceptions.BadRequestException;
import org.waveaccess.conferences.models.User;
import org.waveaccess.conferences.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    public UserRepository userRepository;

    @Autowired
    public PasswordEncoder passwordEncoder;

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream().map(x -> UserDto.builder()
                .id(x.id)
                .name(x.name)
                .email(x.email)
                .hashPassword(x.hashPassword)
                .build())
                .collect(Collectors.toList());
    }

    @Override
    public void signUp(UserFormDto user) {
        try {
            User newUser = userRepository.save(User.builder()
                    .name(user.name)
                    .email(user.email)
                    .hashPassword(passwordEncoder.encode(user.password))
                    .role(User.Role.LISTENER)
                    .build());
        }
        catch (Exception e) {
            throw new BadRequestException(e);
        }
    }

    @Override
    public void updateUserById(UserForUpdateDto userDto) {
        User user = userRepository.findById(userDto.id).orElseThrow(() -> new UsernameNotFoundException("User is not found"));
        user.name = user.name;
        try {
            user.role = User.Role.valueOf(userDto.role);
        }
        catch (IllegalArgumentException e) {
            throw new BadRequestException("No such user role");
        }
        userRepository.save(user);
    }

    @Override
    public Boolean deleteUserById(Long id) {
        try {
            userRepository.deleteById(id);
        }
        catch (EmptyResultDataAccessException e) {
            throw new BadRequestException("User is not found");
        }
        return true;
    }
}
