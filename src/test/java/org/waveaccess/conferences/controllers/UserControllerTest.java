package org.waveaccess.conferences.controllers;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.waveaccess.conferences.dto.UserDto;
import org.waveaccess.conferences.services.UserService;

import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@DisplayName(value = "UserController is working when")
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @BeforeEach
    public void setUp() {

    }

    @Nested
    @DisplayNameGeneration(value = DisplayNameGenerator.ReplaceUnderscores.class)
    @DisplayName("getAllUsers() is working")
    class GetAllUsers {
        @ParameterizedTest
        @ValueSource(strings = {"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwicm9sZSI6IkFETUlOIiwibmFtZSI6ImFkbWluIiwiZW1haWwiOiJhZG1pbkBjb25mZXJlbmVzLmNvbSJ9.RraDEljShog-k4Z4-_Z_GL2uaKWdKUqrQnflNEs1h_o"})
        public void return_all_users_for_admin(String token) throws Exception {
            mockMvc.perform(get("/api/users")
                    .header("X-access-token", token))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].id", is(1)))
                    .andExpect(jsonPath("$[0].name", is("Dina")))
                    .andExpect(jsonPath("$[0].email", is("dina@gmail.com")))
                    .andExpect(jsonPath("$[0].hashPassword", is("$2y$12$xdR6gJl4u64BLIzvyz4A/uDsMuuMSKxkjUqAwGbrp9r9ywuEsSj/e")));
        }

        @ParameterizedTest
        @ValueSource(strings = {"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIyIiwicm9sZSI6IlBSRVNFTlRFUiIsIm5hbWUiOiJEYW5hIiwiZW1haWwiOiJkYW5hQGdtYWlsLmNvbSJ9.jX6lzqHt1OYN18Q-KB_Gk3CZ0GisbtNpPB_UxzB5c8k"})
        public void return_all_users_for_presenter(String token) throws Exception {
            mockMvc.perform(get("/api/users")
                    .header("X-access-token", token))
                    .andDo(print())
                    .andExpect(status().isForbidden());
        }

        @ParameterizedTest
        @ValueSource(strings = {"eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI0Iiwicm9sZSI6IkxJU1RFTkVSIiwibmFtZSI6IkRpbmEiLCJlbWFpbCI6ImRpbmFAZ21haWwuY29tIn0.eetgklGpmDKpWj14OdlMjU7pwACkQLkgdmZoUgzopAg"})
        public void return_all_users_for_listener(String token) throws Exception {
            mockMvc.perform(get("/api/users")
                    .header("X-access-token", token))
                    .andDo(print())
                    .andExpect(status().isForbidden());
        }
    }
}

