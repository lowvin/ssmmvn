package com.cn.lw.service.impl;

/**
 * Created by 罗文 on 2016/12/26.
 */
import javax.annotation.Resource;
import javax.annotation.Resources;

import com.cn.lw.dao.UserDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cn.lw.pojo.User;
import com.cn.lw.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    public User getUserById(int userId) {
        // TODO Auto-generated method stub
        return this.userDao.selectByPrimaryKey(userId);
//        return null;
    }


    public User checkLogin(String username,String password){
        User user = userDao.getUserByName(username);
        if(user!=null&&user.getPassword().equals(password)){
            return user;
        }
        return null;
    }

    public User register(User user){
        userDao.insert(user);
        user = userDao.getUserByName(user.getUserName());
        return user;
    }
}
