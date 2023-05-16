package com.zzx.service;

import com.zzx.entity.User;

import java.util.List;

public interface IUserService {
    /**
     * 查询全部用户
     * @return
     */
    List<User> getUser() throws InterruptedException;

    /**
     * 根据id查询用户
     * @param id
     * @return
     */
    User findById(Long id);
}
