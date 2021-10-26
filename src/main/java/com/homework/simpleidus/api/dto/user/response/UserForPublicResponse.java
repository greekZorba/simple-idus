package com.homework.simpleidus.api.dto.user.response;

import com.homework.simpleidus.domain.entity.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserForPublicResponse implements Serializable {
    private String uuid;

    private String loginId;

    private String nickname;

    private String email;

    public static UserForPublicResponse of(User user) {
        return UserForPublicResponse.builder()
                .uuid(user.getUuid())
                .loginId(user.getLoginId())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .build();
    }
}
