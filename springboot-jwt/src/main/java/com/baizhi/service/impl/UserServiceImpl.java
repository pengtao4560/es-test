/*
 * @author yjiewei
 * @date 2021/8/17 22:00
 */
package com.baizhi.service.impl;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public User login(User user) {
        User login = userDao.login(user); // 根据用户名和密码
        if (login != null) {
            return login;
        }
        throw new RuntimeException("登录失败...");
    }
}
