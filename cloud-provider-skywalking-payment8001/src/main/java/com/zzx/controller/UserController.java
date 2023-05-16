package com.zzx.controller;

import com.zzx.entity.User;
import com.zzx.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("user")
@RestController
public class UserController {
    @Autowired
    private IUserService iUserService;

    /**
     * 查询全部用户
     * @return
     */
    @GetMapping("findByAll")
    public List<User> getUser() throws InterruptedException {
        return iUserService.getUser();
    }

    /**
     * 根据用户id查询用户
     * @param id
     * @return
     */
    @GetMapping("findById")
    public User findById(Long id){
        return iUserService.findById(id);
    }

}
