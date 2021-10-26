package com.homework.simpleidus.api.dto.auth.request;

import lombok.*;

@NoArgsConstructor
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
public class LoginRequest {
    private String loginId;

    private String password;
}
