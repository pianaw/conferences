package org.waveaccess.conferences.services;

import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.waveaccess.conferences.dto.UserDto;
import org.waveaccess.conferences.models.User;
import org.waveaccess.conferences.repositories.UserRepository;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DisplayName(value = "UserServiceImpl is working")
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService = new UserServiceImpl();

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        when(userRepository.findAll()).thenReturn(Arrays.stream(
                new User[]{
                        User.builder()
                                .id(1L)
                                .name("Dina")
                                .email("dina@gmail.com")
                                .hashPassword("$2y$12$xdR6gJl4u64BLIzvyz4A/uDsMuuMSKxkjUqAwGbrp9r9ywuEsSj/e")
                                .build()})
                .collect(Collectors.toList()));
    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("getAllUsers() is working")
    class GetAllUsers {

        @Test
        public void return_all_users() {
            assertEquals(Arrays.stream(
                    new UserDto[]{
                            UserDto.builder()
                                    .id(1L)
                                    .name("Dina")
                                    .email("dina@gmail.com")
                                    .hashPassword("$2y$12$xdR6gJl4u64BLIzvyz4A/uDsMuuMSKxkjUqAwGbrp9r9ywuEsSj/e")
                                    .build()})
                    .collect(Collectors.toList()),userService.getAllUsers());
        }
    }


}
