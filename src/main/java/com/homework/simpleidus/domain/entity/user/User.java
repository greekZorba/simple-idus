package com.homework.simpleidus.domain.entity.user;

import com.homework.simpleidus.domain.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.regex.Pattern;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "users")
public class User extends BaseEntity {
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    @Column
    @Enumerated(EnumType.STRING)
    private GenderType gender;

    public void validate() {
        if (!Pattern.matches("^[a-zA-Z가-힣]{1,20}$", this.name)) {
            throw new IllegalArgumentException("한글, 영문 대소문자만 허용, 입력된 name : " + this.name);
        }

        if (!Pattern.matches("^[a-z]{1,30}$", this.nickname)) {
            throw new IllegalArgumentException("소문자만 허용가능, 입력된 nickname : " + this.nickname);
        }

        if (!Pattern.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{10,30}$", this.password)) {
            throw new IllegalArgumentException("password는 영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함되어야함");
        }

        if (!Pattern.matches("^[0-9]{1,20}$", this.phoneNumber)) {
            throw new IllegalArgumentException("숫자만 허용가능, 입력된 phoneNumber : " + this.phoneNumber);
        }

        if (!Pattern.matches("^(.+)@(.+){1,100}$", this.email)) {
            throw new IllegalArgumentException("이메일 형식에 맞지 않음, 입력된 email : " + this.email);
        }

    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.password = encryptedPassword;
    }
}
