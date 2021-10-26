package com.homework.simpleidus.api.service.user;

import com.homework.simpleidus.api.dto.user.request.UserCreateRequest;
import com.homework.simpleidus.domain.entity.user.User;
import com.homework.simpleidus.domain.repository.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private UserService userService;

    @BeforeEach
    void setUp() {
        passwordEncoder = new BCryptPasswordEncoder();
        userService = new UserService(userRepository, passwordEncoder);

        lenient().when(userRepository.save(any(User.class))).thenReturn(User.builder().build());
    }

    @Test
    void user를_생성한다() {
        // given
        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .loginId("smiling.jinhak@gmail.com")
                .name("조르바")
                .nickname("zorba")
                .password("helloworldAAAAA.12")
                .phoneNumber("01012345678")
                .email("smiling.jinhak@gmail.com")
                .build();

        // when
        userService.create(userCreateRequest);

        // not occur error
    }
}