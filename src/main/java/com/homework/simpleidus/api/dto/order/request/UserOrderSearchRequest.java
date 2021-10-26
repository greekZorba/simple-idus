package com.homework.simpleidus.api.dto.order.request;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.lang.Nullable;

import java.io.Serializable;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
public class UserOrderSearchRequest implements Serializable {
    private int page;

    private int pageSize;

    @Nullable
    private String userName;

    @Nullable
    private String email;

    public static UserOrderSearchRequest of(int page, int pageSize, String userName, String email) {
        return UserOrderSearchRequest.builder()
                .page(page)
                .pageSize(pageSize)
                .userName(userName != null && userName.isEmpty() ? null : userName)
                .email(email != null && email.isEmpty() ? null : email)
                .build();
    }
}
