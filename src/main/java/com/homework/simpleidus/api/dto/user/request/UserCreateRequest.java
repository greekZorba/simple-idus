package com.homework.simpleidus.api.dto.user.request;

import com.fasterxml.uuid.Generators;
import com.homework.simpleidus.domain.entity.user.GenderType;
import com.homework.simpleidus.domain.entity.user.User;
import com.sun.istack.NotNull;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class UserCreateRequest implements Serializable {
    @NotNull
    private String loginId;

    @NotNull
    private String name;

    @NotNull
    private String nickname;

    @NotNull
    private String password;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String email;

    private GenderType gender;

    public User toEntity(String encryptedPassword) {
        User user = User.builder()
                .uuid(Generators.timeBasedGenerator().generate().toString())
                .loginId(this.loginId)
                .name(this.name)
                .nickname(this.nickname)
                .password(this.password)
                .phoneNumber(this.phoneNumber)
                .email(this.email)
                .gender(this.gender)
                .build();
        user.validate();
        user.setEncryptedPassword(encryptedPassword);

        return user;
    }

}
