package com.cn.lw.dao;

import com.cn.lw.pojo.User;

/**
 * Created by 罗文 on 2016/12/26.
 */
public interface UserDao {

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User getUserByName(String username);
}
