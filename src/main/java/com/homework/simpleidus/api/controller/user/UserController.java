package com.homework.simpleidus.api.controller.user;

import com.homework.simpleidus.api.dto.user.request.UserCreateRequest;
import com.homework.simpleidus.api.dto.user.response.UserForPrivateResponse;
import com.homework.simpleidus.api.dto.user.response.UserForPublicResponse;
import com.homework.simpleidus.api.service.user.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @ApiOperation(
            value = "회원 생성",
            notes = "회를 생성합니다."
    )
    @PostMapping("/api/user")
    public void createUser(@RequestBody UserCreateRequest request) {
        userService.create(request);
    }

    @ApiOperation(
            value = "회원 공개가능한 정보 조회",
            notes = "회원의 개인정보를 제외하고 조회합니다."
    )
    @GetMapping("/api/users/{userUuid}")
    public UserForPublicResponse getUserForPublic(@PathVariable String userUuid) {
        return UserForPublicResponse.of(userService.getUserByUuid(userUuid));
    }

    @ApiOperation(
            value = "개인정보를 포함한 회원 정보 조회",
            notes = "회원의 개인정보를 포함하여 조회합니다."
    )
    @GetMapping("/api/users/{userUuid}/detail")
    public UserForPrivateResponse getUserForPrivate(@PathVariable String userUuid) {
        return UserForPrivateResponse.of(userService.getUserByUuid(userUuid));
    }

}
