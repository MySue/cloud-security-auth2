package com.sxm.uaa.dao;

import com.sxm.uaa.mocdel.PermissionDto;
import com.sxm.uaa.mocdel.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Su
 * @create 2020/4/13
 */
@Repository
public class UserDao {

    @Autowired
    JdbcTemplate jdbcTemplate;

    /**
     * 根据账号查询用户信息
     * @param username
     * @return
     */
    public UserDto getUserByUsername(String username){
        String sql = "select id,username,password,fullname,mobile from t_user where username = ?";
        // 连接数据库查询用户
        List<UserDto> query = jdbcTemplate.query(sql, new Object[]{username}, new BeanPropertyRowMapper<>(UserDto.class));
        return query.get(0);
    }

    /**
     * 根据用户id查询用户权限
     * @param userId
     * @return
     */
    public List<String> findPermissionByUserId(String userId){
        String sql = "SELECT * FROM t_permission WHERE id IN(" +
                "SELECT permission_id FROM t_role_permission WHERE role_id IN(" +
                    "SELECT role_id FROM t_user_role WHERE user_id = ?))";
        List<PermissionDto> query = jdbcTemplate.query(sql, new Object[]{userId}, new BeanPropertyRowMapper<>(PermissionDto.class));
        List<String> permissions = new ArrayList<>();
        query.forEach(c -> permissions.add(c.getCode()));
        return permissions;
    }
}
