package com.homework.simpleidus.api.dto.order.request;

import lombok.*;
import org.springframework.lang.Nullable;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class UserOrderSearchRequest implements Serializable {
    private int page;

    private int pageSize;

    @Nullable
    private String name;

    @Nullable
    private String email;

    public static UserOrderSearchRequest of(int page, int pageSize, String name, String email) {
        return UserOrderSearchRequest.builder()
                .page(page)
                .pageSize(pageSize)
                .name(name != null && name.isEmpty() ? null : name)
                .email(email != null && email.isEmpty() ? null : email)
                .build();
    }
}
