package com.sxm.uaa.mocdel;

import lombok.Data;

/**
 * @Author Su
 * @create 2020/4/13
 */
@Data
public class UserDto {

    private String id;
    private String username;
    private String password;
    private String fullname;
    private String mobile;
}
