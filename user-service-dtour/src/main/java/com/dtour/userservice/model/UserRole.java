package com.dtour.userservice.model;

import com.dtour.userservice.typelist.UserRoleEnum;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Entity
@Setter
public class UserRole extends BaseModel {

    private String name;
    private UserRoleEnum code;
}
