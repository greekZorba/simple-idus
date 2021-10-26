package com.homework.simpleidus.api.dto.user.response;

import com.homework.simpleidus.domain.entity.user.GenderType;
import com.homework.simpleidus.domain.entity.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserForPrivateResponse implements Serializable {
    private String uuid;

    private String loginId;

    private String name;

    private String nickname;

    private String phoneNumber;

    private String email;

    private GenderType gender;

    public static UserForPrivateResponse of(User user) {
        return UserForPrivateResponse.builder()
                .uuid(user.getUuid())
                .loginId(user.getLoginId())
                .name(user.getName())
                .nickname(user.getNickname())
                .phoneNumber(user.getPhoneNumber())
                .email(user.getEmail())
                .gender(user.getGender())
                .build();
    }
}
