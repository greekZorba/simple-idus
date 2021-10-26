package com.homework.simpleidus.api.controller.order;

import com.homework.simpleidus.api.dto.PageResponse;
import com.homework.simpleidus.api.dto.order.request.UserOrderSearchRequest;
import com.homework.simpleidus.api.dto.order.response.OrderResponse;
import com.homework.simpleidus.api.dto.order.response.UserOrderResponse;
import com.homework.simpleidus.api.service.order.UserOrderService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
public class UserOrderController {

    private final UserOrderService userOrderService;

    @ApiOperation(
            value = "회원과 회원의 마지막 주문정보를 페이징 조회",
            notes = "검색조건에 맞춰 회원 회원의 마지막 주문정보를 페이징 조회합니다"
    )
    @GetMapping("/api/users")
    public PageResponse<UserOrderResponse> searchUserWithLastOrder(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "pageSize", defaultValue = "15") int pageSize,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "email", required = false) String email
    ) {

        return PageResponse.of(
                userOrderService.getUserWithOrders(UserOrderSearchRequest.of(page, pageSize, name, email)),
                (value) -> UserOrderResponse.of(value)
        );
    }

    @ApiOperation(
            value = "회원의 전체 주문정보 조회",
            notes = "회원의 전체 주문정보를 조회합니다"
    )
    @GetMapping("/api/users/{userUuid}/orders")
    public List<OrderResponse> getUserOrders(@PathVariable String userUuid) {
        return userOrderService.getOrdersByUser(userUuid).stream()
                .map(OrderResponse::of)
                .collect(Collectors.toList());
    }
}
