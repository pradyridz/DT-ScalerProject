package com.dtour.userservice.model;

import com.dtour.userservice.typelist.UserStatusEnum;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class UserStatus extends BaseModel {
    private String name;
    private UserStatusEnum code;
}
