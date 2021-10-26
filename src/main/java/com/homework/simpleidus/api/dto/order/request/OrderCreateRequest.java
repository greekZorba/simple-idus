package com.homework.simpleidus.api.dto.order.request;

import com.fasterxml.uuid.Generators;
import com.homework.simpleidus.common.util.date.LocalDateUtils;
import com.homework.simpleidus.domain.entity.order.Order;
import com.homework.simpleidus.domain.entity.user.User;
import com.sun.istack.NotNull;
import lombok.*;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class OrderCreateRequest implements Serializable {
    @NotNull
    private String productName;

    public Order toEntity(User user) {
        return Order.builder()
                .uuid(Generators.timeBasedGenerator().generate().toString())
                .productName(this.productName)
                .paidAt(LocalDateUtils.getNowInKorea())
                .user(user)
                .build();
    }
}
