package com.homework.simpleidus.api.dto.user.request;

import com.homework.simpleidus.domain.entity.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UserCreateRequestTest {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    void 암호화된_password가_입력된_user_엔티티_생성_성공() {
        // given
        String rawPassword = "helloworldAAAAA.12";
        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .loginId("smiling.jinhak@gmail.com")
                .name("조르바")
                .nickname("zorba")
                .password(rawPassword)
                .phoneNumber("01012345678")
                .email("smiling.jinhak@gmail.com")
                .build();
        String encryptedPassword = passwordEncoder.encode(userCreateRequest.getPassword());

        // when
        User user = userCreateRequest.toEntity(encryptedPassword);

        // then
        assertNotNull(user);
        assertTrue(passwordEncoder.matches(rawPassword, user.getPassword()));
    }

}