package com.shunshun.shirospringshun.service.impl;

import com.shunshun.shirospringshun.dao.UserMapper;
import com.shunshun.shirospringshun.domain.User;
import com.shunshun.shirospringshun.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Action;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public Integer editPassword(User user) {
        return userMapper.editPassword(user);
    }
}
