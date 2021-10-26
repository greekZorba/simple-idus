package com.homework.simpleidus.api.dto.order.response;

import com.homework.simpleidus.domain.entity.BaseEntity;
import com.homework.simpleidus.domain.entity.order.Order;
import com.homework.simpleidus.domain.entity.user.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Optional;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class UserOrderResponse implements Serializable {
    private String uuid;

    private String loginId;

    private String nickname;

    private String email;

    private OrderResponse lastOrder;

    public static UserOrderResponse of(User user) {
        Optional<Order> lastOrder = user.getOrders().stream()
                .max(Comparator.comparing(BaseEntity::getCreatedAt));

        return UserOrderResponse.builder()
                .uuid(user.getUuid())
                .loginId(user.getLoginId())
                .nickname(user.getNickname())
                .email(user.getEmail())
                .lastOrder(lastOrder.isPresent() ? OrderResponse.of(lastOrder.get()) : null)
                .build();
    }
}
