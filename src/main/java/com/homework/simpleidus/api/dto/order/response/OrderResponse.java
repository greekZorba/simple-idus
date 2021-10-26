package com.homework.simpleidus.api.dto.order.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.homework.simpleidus.domain.entity.order.Order;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OrderResponse implements Serializable {
    private String uuid;

    private String productName;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime paidAt;

    public static OrderResponse of(Order order) {
        return OrderResponse.builder()
                .uuid(order.getUuid())
                .productName(order.getProductName())
                .paidAt(order.getPaidAt())
                .build();
    }
}
