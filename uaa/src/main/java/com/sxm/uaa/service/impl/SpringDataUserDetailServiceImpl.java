package com.sxm.uaa.service.impl;

import com.alibaba.fastjson.JSON;
import com.sxm.uaa.dao.UserDao;
import com.sxm.uaa.mocdel.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Su
 * @create 2020/4/10
 */
@Service
public class SpringDataUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    UserDao userDao;

    /**
     * 根据 账号查询用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 将来连接数据库根据账号查询用户信息
        // 登录账号
        System.out.println("username = " + username);
        UserDto user = userDao.getUserByUsername(username);
        if (user == null){
            return null;
        }

        // 根据用户的id查询用户的权限
        List<String> permissions = userDao.findPermissionByUserId(user.getId());
        // 将permission转成数组
        String[] permissionArray = new String[permissions.size()];
        permissions.toArray(permissionArray);
        // 将userDto转成json
        String principal = JSON.toJSONString(user);
        return User.withUsername(principal).password(user.getPassword()).authorities(permissionArray).build();
    }
}
