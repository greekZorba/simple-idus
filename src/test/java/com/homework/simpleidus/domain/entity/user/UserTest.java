package com.homework.simpleidus.domain.entity.user;

import com.fasterxml.uuid.Generators;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {

    @Test
    void uuid_null_또는_empty인_경우_예외발생() {
        // given
        User emptyUuidUser = User.builder()
                .uuid("")
                .loginId("smiling.jinhakgmail.com")
                .name("123")
                .nickname("zorba")
                .password("helloworld12A.")
                .email("smiling.jinhak@gmail.com")
                .build();

        User nullUuidUser = User.builder()
                .uuid(null)
                .loginId("smiling.jinhakgmail.com")
                .name("123")
                .nickname("zorba")
                .password("helloworld12A.")
                .email("smiling.jinhak@gmail.com")
                .build();

        assertThrows(
                IllegalStateException.class,
                () -> emptyUuidUser.validate(),
                "uuid가 존재하지 않음"
        );

        assertThrows(
                IllegalStateException.class,
                () -> nullUuidUser.validate(),
                "uuid가 존재하지 않음"
        );
    }

    @Test
    void 잘못된_loginId_형식으로_예외발생() {
        // given
        User wrongLoginIdUser = User.builder()
                .uuid(Generators.timeBasedGenerator().generate().toString())
                .loginId("smiling.jinhakgmail.com")
                .name("123")
                .nickname("zorba")
                .password("helloworld12A.")
                .email("smiling.jinhak@gmail.com")
                .build();

        // when, then
        assertThrows(
                IllegalArgumentException.class,
                () -> wrongLoginIdUser.validate(),
                "아이디 형식에 맞지 않음, 입력된 loginId : " + wrongLoginIdUser.getLoginId()
        );
    }

    @Test
    void 잘못된_name_형식으로_예외발생() {
        // given
        User wrongNameUser = User.builder()
                .uuid(Generators.timeBasedGenerator().generate().toString())
                .loginId("smiling.jinhak@gmail.com")
                .name("123")
                .nickname("zorba")
                .password("helloworld12A.")
                .email("smiling.jinhak@gmail.com")
                .build();
        User emptyNameUser = User.builder()
                .uuid(Generators.timeBasedGenerator().generate().toString())
                .loginId("smiling.jinhak@gmail.com")
                .name("")
                .nickname("zorba")
                .password("helloworld12A.")
                .email("smiling.jinhak@gmail.com")
                .build();

        // when, then
        assertThrows(
                IllegalArgumentException.class,
                () -> wrongNameUser.validate(),
                "한글, 영문 대소문자만 허용, 입력된 name : " + wrongNameUser.getName()
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> emptyNameUser.validate(),
                "한글, 영문 대소문자만 허용, 입력된 name : " + emptyNameUser.getName()
        );
    }

    @Test
    void 잘못된_nickname_형식으로_예외발생() {
        // given
        User wrongNicknameUser = User.builder()
                .uuid(Generators.timeBasedGenerator().generate().toString())
                .loginId("smiling.jinhak@gmail.com")
                .name("조르바")
                .nickname("하이")
                .password("helloworld12A.")
                .email("smiling.jinhak@gmail.com")
                .build();

        // when, then
        assertThrows(IllegalArgumentException.class,
                () -> wrongNicknameUser.validate(),
                "소문자만 허용가능, 입력된 nickname : " + wrongNicknameUser.getNickname()
        );
    }

    @Test
    void 잘못된_password_형식으로_예외발생() {
        // given
        User emptyUppercasePasswordUser = User.builder()
                .uuid(Generators.timeBasedGenerator().generate().toString())
                .loginId("smiling.jinhak@gmail.com")
                .name("조르바")
                .nickname("zorba")
                .password("helloworld12.")
                .email("smiling.jinhak@gmail.com")
                .build();
        User emptyLowercasePasswordUser = User.builder()
                .uuid(Generators.timeBasedGenerator().generate().toString())
                .loginId("smiling.jinhak@gmail.com")
                .name("조르바")
                .nickname("zorba")
                .password("HELLOWORLD12.A")
                .email("smiling.jinhak@gmail.com")
                .build();
        User emptySpecialCharacterPasswordUser = User.builder()
                .uuid(Generators.timeBasedGenerator().generate().toString())
                .loginId("smiling.jinhak@gmail.com")
                .name("조르바")
                .nickname("하이")
                .password("helloworld12A")
                .email("smiling.jinhak@gmail.com")
                .build();
        User emptyNumberPasswordUser = User.builder()
                .uuid(Generators.timeBasedGenerator().generate().toString())
                .loginId("smiling.jinhak@gmail.com")
                .name("조르바")
                .nickname("하이")
                .password("helloworldA.")
                .email("smiling.jinhak@gmail.com")
                .build();
        User shortPasswordUser = User.builder()
                .uuid(Generators.timeBasedGenerator().generate().toString())
                .loginId("smiling.jinhak@gmail.com")
                .name("조르바")
                .nickname("하이")
                .password("worldA.12")
                .email("smiling.jinhak@gmail.com")
                .build();
        User longPasswordUser = User.builder()
                .uuid(Generators.timeBasedGenerator().generate().toString())
                .loginId("smiling.jinhak@gmail.com")
                .name("조르바")
                .nickname("하이")
                .password("helloworldAAAAA.12helloworldAAA")
                .email("smiling.jinhak@gmail.com")
                .build();

        // when, then
        assertThrows(IllegalArgumentException.class,
                () -> emptyUppercasePasswordUser.validate(),
                "password는 영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함되어야함"
        );

        assertThrows(IllegalArgumentException.class,
                () -> emptyLowercasePasswordUser.validate(),
                "password는 영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함되어야함"
        );

        assertThrows(IllegalArgumentException.class,
                () -> emptySpecialCharacterPasswordUser.validate(),
                "password는 영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함되어야함"
        );

        assertThrows(IllegalArgumentException.class,
                () -> emptyNumberPasswordUser.validate(),
                "password는 영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함되어야함"
        );

        assertThrows(IllegalArgumentException.class,
                () -> shortPasswordUser.validate(),
                "password는 영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함되어야함"
        );

        assertThrows(IllegalArgumentException.class,
                () -> longPasswordUser.validate(),
                "password는 영문 대문자, 영문 소문자, 특수 문자, 숫자 각 1개 이상씩 포함되어야함"
        );

    }

    @Test
    void 잘못된_phoneNumber_형식으로_예외발생() {
        User wrongPhoneNumberUser = User.builder()
                .uuid(Generators.timeBasedGenerator().generate().toString())
                .name("조르바")
                .loginId("smiling.jinhak@gmail.com")
                .nickname("zorba")
                .password("helloworldAAAAA.12")
                .phoneNumber("010-1234-5678")
                .email("smiling.jinhak@gmail.com")
                .build();

        assertThrows(IllegalArgumentException.class,
                () -> wrongPhoneNumberUser.validate(),
                "숫자만 허용가능, 입력된 phoneNumber : " + wrongPhoneNumberUser.getPhoneNumber()
        );
    }

    @Test
    void 잘못된_email_형식으로_예외발생() {
        User wrongEmailUser = User.builder()
                .uuid(Generators.timeBasedGenerator().generate().toString())
                .loginId("smiling.jinhak@gmail.com")
                .name("조르바")
                .nickname("zorba")
                .password("helloworldAAAAA.12")
                .phoneNumber("010-1234-5678")
                .email("smiling.jinhakgmail.com")
                .build();

        assertThrows(IllegalArgumentException.class,
                () -> wrongEmailUser.validate(),
                "이메일 형식에 맞지 않음, 입력된 email : " + wrongEmailUser.getEmail()
        );
    }

    @Test
    void 올바른형식_입력으로_validation_성공() {
        User user = User.builder()
                .uuid(Generators.timeBasedGenerator().generate().toString())
                .loginId("smiling.jinhak@gmail.com")
                .name("조르바")
                .nickname("zorba")
                .password("helloworldAAAAA.12")
                .phoneNumber("01012345678")
                .email("smiling.jinhak@gmail.com")
                .build();

        // when
        user.validate();

        // not occur error
    }

}