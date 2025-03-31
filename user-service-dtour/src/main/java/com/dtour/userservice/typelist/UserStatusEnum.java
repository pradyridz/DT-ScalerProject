package com.dtour.userservice.typelist;

import lombok.Getter;

@Getter
public enum UserStatusEnum {
    ACTIVE(0),
    INACTIVE(1),
    BLOCKED(2);

    private final int value;

    UserStatusEnum(int value) {
        this.value = value;
    }
}
