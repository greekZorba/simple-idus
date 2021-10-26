package com.homework.simpleidus.api.dto.user;

import com.homework.simpleidus.domain.entity.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class LoginUser {
    private String uuid;

    private String loginId;

    public static LoginUser of(User user) {
        return LoginUser.builder()
                .uuid(user.getUuid())
                .loginId(user.getLoginId())
                .build();
    }
}
