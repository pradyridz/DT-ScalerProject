package com.dtour.userservice.util;

import com.dtour.userservice.model.BaseModel;

import java.util.Date;

public class UserUtil {

    public static BaseModel buildBaseData(BaseModel model) {
        model.setCreatedAt(new Date());
        model.setUpdatedAt(new Date());
        model.setDeleted(false);
        return model;
    }
}
