package com.zues.config;

import com.zues.entity.User;
import org.springframework.beans.factory.FactoryBean;

public class UserFactoryBean implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        return new User();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }
}
