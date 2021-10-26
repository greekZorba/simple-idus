package com.homework.simpleidus.api.service.order;

import com.homework.simpleidus.api.dto.order.request.OrderCreateRequest;
import com.homework.simpleidus.api.service.user.UserService;
import com.homework.simpleidus.domain.repository.order.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserService userService;

    @Transactional
    public void create(OrderCreateRequest orderCreateRequest, String userUuid) {
        orderRepository.save(orderCreateRequest.toEntity(userService.getUserByUuid(userUuid)));
    }
}
