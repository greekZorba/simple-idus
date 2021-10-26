package com.homework.simpleidus.api.controller.order;

import com.homework.simpleidus.api.dto.PageResponse;
import com.homework.simpleidus.api.dto.order.request.UserOrderSearchRequest;
import com.homework.simpleidus.api.dto.order.response.UserOrderResponse;
import com.homework.simpleidus.api.service.order.UserOrderService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserOrderController {

    private final UserOrderService userOrderService;

    @ApiOperation(
            value = "사용자와 사용자의 마지막 주문정보를 페이징 조회",
            notes = "검색조건에 맞춰 사용자와 사용자의 마지막 주문정보를 페이징 조회합니다"
    )
    @GetMapping("/api/users")
    public PageResponse<UserOrderResponse> searchUserWithLastOrder(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "pageSize", defaultValue = "15") int pageSize,
            @RequestParam(value = "userName", required = false) String userName,
            @RequestParam(value = "email", required = false) String email
    ) {

        return PageResponse.of(
                userOrderService.getUserWithOrders(UserOrderSearchRequest.of(page, pageSize, userName, email)),
                (value) -> UserOrderResponse.of(value)
        );
    }
}
