package com.homework.simpleidus.api.service.user;

import com.homework.simpleidus.api.dto.user.request.UserCreateRequest;
import com.homework.simpleidus.api.exception.ExistException;
import com.homework.simpleidus.api.exception.NotFoundException;
import com.homework.simpleidus.domain.entity.user.User;
import com.homework.simpleidus.domain.repository.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void create(UserCreateRequest request) {
        if (userRepository.findByLoginIdAndDeletedFalse(request.getLoginId()).isPresent()) {
            throw new ExistException("이미 존재하는 유저 loginId : " + request.getLoginId());
        }

        userRepository.save(request.toEntity(passwordEncoder.encode(request.getPassword())));
    }

    @Transactional(readOnly = true)
    public User getUserByLoginId(String loginId) {
        Optional<User> user = userRepository.findByLoginIdAndDeletedFalse(loginId);

        if (user.isEmpty()) {
            throw new NotFoundException("존재하지 않는 loginId : " + loginId);
        }

        return user.get();
    }

    @Transactional(readOnly = true)
    public User getUserByUuid(String uuid) {
        Optional<User> user = userRepository.findByUuidAndDeletedFalse(uuid);

        if (user.isEmpty()) {
            throw new NotFoundException("존재하지 않는 uuid : " + uuid);
        }

        return user.get();
    }


}
