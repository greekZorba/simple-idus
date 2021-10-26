package com.homework.simpleidus.api.service.order;

import com.homework.simpleidus.api.dto.order.request.UserOrderSearchRequest;
import com.homework.simpleidus.api.exception.NotFoundException;
import com.homework.simpleidus.domain.entity.order.Order;
import com.homework.simpleidus.domain.entity.user.User;
import com.homework.simpleidus.domain.repository.user.UserRepository;
import com.homework.simpleidus.domain.specification.UserSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserOrderService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<User> getUserWithOrders(UserOrderSearchRequest request) {
        Specification specification = UserSpecification.from(request.getName(), request.getEmail());

        Page<User> users = userRepository.findAll(specification, PageRequest.of(request.getPage() - 1, request.getPageSize()));
        List<User> usersWithOrders = userRepository.findAllWithOrdersInIds(
                users.getContent().stream()
                        .map(User::getId)
                        .collect(Collectors.toList())
        );

        return new PageImpl(
                usersWithOrders,
                users.getPageable(),
                users.getTotalElements()
        );
    }

    @Transactional(readOnly = true)
    public List<Order> getOrdersByUser(String userUuid) {
        Optional<User> user = userRepository.findByUuidAndDeletedFalse(userUuid);

        if (user.isEmpty()) {
            throw new NotFoundException("존재하지 않는 uuid : " + userUuid);
        }

        return userRepository.findByUuidAndDeletedFalse(userUuid).get().getOrders();
    }
}
