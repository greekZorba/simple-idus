package com.homework.simpleidus.api.service.auth;

import com.homework.simpleidus.api.dto.auth.request.LoginRequest;
import com.homework.simpleidus.api.service.user.UserService;
import com.homework.simpleidus.domain.entity.user.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

    public User login(LoginRequest loginRequest) {
        User user = userService.getUserByLoginId(loginRequest.getLoginId());

        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("패스워드가 틀렸습니다. loginId : " + user.getLoginId());
        }

        return user;
    }
}
