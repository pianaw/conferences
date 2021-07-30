package org.waveaccess.conferences.services;

import org.waveaccess.conferences.dto.UserForUpdateDto;
import org.waveaccess.conferences.dto.UserDto;
import org.waveaccess.conferences.dto.UserFormDto;

import java.util.List;

public interface UserService {

    void signUp(UserFormDto user);

    List<UserDto> getAllUsers();

    Boolean deleteUserById(Long id);

    void updateUserById(UserForUpdateDto userRoleDto) throws Exception;
}
