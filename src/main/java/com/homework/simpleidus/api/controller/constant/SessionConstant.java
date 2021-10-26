package com.homework.simpleidus.api.controller.constant;

import lombok.Getter;

public enum SessionConstant {
    LOGIN_USER("user");

    @Getter
    private final String value;

    private SessionConstant(String value) {
        this.value = value;
    }
}
