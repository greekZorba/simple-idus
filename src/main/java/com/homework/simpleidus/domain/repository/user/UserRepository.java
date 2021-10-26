package com.homework.simpleidus.domain.repository.user;

import com.homework.simpleidus.domain.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findByLoginIdAndDeletedFalse(String loginId);

    Optional<User> findByUuidAndDeletedFalse(String uuid);

    @Query("select distinct user from User user " +
            "join fetch user.orders " +
            "where user.id in :ids and user.deleted = false "
    )
    List<User> findAllWithOrdersInIds(List<Long> ids);
}
