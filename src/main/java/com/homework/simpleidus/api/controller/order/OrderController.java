package com.homework.simpleidus.api.controller.order;

import com.homework.simpleidus.api.configuration.security.Login;
import com.homework.simpleidus.api.dto.order.request.OrderCreateRequest;
import com.homework.simpleidus.api.dto.user.LoginUser;
import com.homework.simpleidus.api.service.order.OrderService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class OrderController {

    private final OrderService orderService;

    @ApiOperation(
            value = "주문 생성",
            notes = "(* 로그인 필요)주문자가 요청한 주문을 생성합니다."
    )
    @PostMapping("/api/order")
    public void create(@RequestBody OrderCreateRequest orderCreateRequest, @Login LoginUser loginUser) {
        orderService.create(orderCreateRequest, loginUser.getUuid());
    }

}
