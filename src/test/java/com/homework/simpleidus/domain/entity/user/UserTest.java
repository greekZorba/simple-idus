package com.homework.simpleidus.domain.entity.user;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

//@DataJpaTest
@SpringBootTest
class UserTest {

    @Test
    public void 빌더패턴_생성_테스트() {

        User user = User.builder().email("d").build();
        System.out.println("createdAt : " + user.getCreatedAt());
        System.out.println("updatedAt : " + user.getUpdatedAt());
        System.out.println("deleted : " + user.isDeleted());

    }
}