package com.dtour.userservice.typelist;

import lombok.Getter;

@Getter
public enum UserRoleEnum {
    AGENT(0),
    ADMIN(1),
    SUPER_USER(2),
    INTEGRATION_USER(3);

    private final int value;

    UserRoleEnum(int value) {
        this.value = value;
    }
}
