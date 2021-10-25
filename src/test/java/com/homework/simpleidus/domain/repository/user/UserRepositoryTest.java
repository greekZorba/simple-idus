package com.homework.simpleidus.domain.repository.user;

import com.homework.simpleidus.domain.entity.user.GenderType;
import com.homework.simpleidus.domain.entity.user.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    private final User fixtureUser = User.builder()
            .name("조르바")
            .nickname("zorba")
            .phoneNumber("01012345678")
            .password("hello12A.")
            .email("smiling.jinhak@gmail.com")
            .gender(GenderType.MALE)
            .build();

    @Test
    void user_저장후_조회_성공() {
        // when
        User savedUser = userRepository.save(fixtureUser);
        Optional<User> fetchUser = userRepository.findById(savedUser.getId());

        // then
        assertTrue(fetchUser.isPresent());
        User user = fetchUser.get();
        assertNotNull(user.getId());
        assertNotNull(user.getCreatedAt());
        assertNotNull(user.getUpdatedAt());
        assertThat(user.getName(), is(fixtureUser.getName()));
        assertThat(user.getNickname(), is(fixtureUser.getNickname()));
    }

}