package com.homework.simpleidus.api.controller.auth;

import com.homework.simpleidus.api.dto.auth.request.LoginRequest;
import com.homework.simpleidus.api.dto.user.LoginUser;
import com.homework.simpleidus.api.service.auth.AuthService;
import com.homework.simpleidus.domain.entity.user.User;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.homework.simpleidus.api.controller.constant.SessionConstant.LOGIN_USER;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @ApiOperation(
            value = "로그인",
            notes = "사용자를 로그인 합니다."
    )
    @PostMapping("/api/login")
    public void login(@RequestBody LoginRequest loginRequest, HttpServletRequest request) {
        User user = authService.login(loginRequest);
        HttpSession session = request.getSession();
        session.setAttribute(LOGIN_USER.getValue(), LoginUser.of(user));
    }

    @ApiOperation(
            value = "로그아웃",
            notes = "접속중인 사용자를 로그아웃 합니다."
    )
    @PostMapping("/api/logout")
    public void logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);

        if (session != null) {
            session.invalidate();
        }
    }

}
