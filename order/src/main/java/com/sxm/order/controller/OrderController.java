package com.sxm.order.controller;

import com.sxm.order.model.UserDTO;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Su
 * @create 2020/4/14
 */
@RestController
public class OrderController {

    /**
     * @PreAuthorize("hasAnyAuthority('p1')") 拥有p1权限方可访问此url
     * @return
     */
    @GetMapping(value = "/r1")
    @PreAuthorize("hasAuthority('p1')")
    public String r1(){
        // 获取用户身份信息
        UserDTO userDTO = (UserDTO)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userDTO.getFullname() + "访问资源1";
    }
}
