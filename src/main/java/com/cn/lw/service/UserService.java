package com.cn.lw.service;

import com.cn.lw.pojo.User;
import org.springframework.stereotype.Service;

/**
 * Created by 罗文 on 2016/12/26.
 */
public interface UserService {

    public User getUserById(int userId);

    // 通过用户名及密码核查用户登录
    public User checkLogin(String username, String password);

    public User register(User user);
}
