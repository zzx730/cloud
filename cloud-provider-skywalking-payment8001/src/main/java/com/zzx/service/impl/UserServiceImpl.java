package com.zzx.service.impl;

import com.zzx.entity.User;
import com.zzx.mapper.UserMapper;
import com.zzx.service.IUserService;
import org.apache.skywalking.apm.toolkit.trace.Trace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    /**
     * 查询全部用户
     * @return
     */
    @Trace
    @Override
    public List<User> getUser() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        return userMapper.selectList(null);
    }
    /**
     * 根据用户id查询用户
     * @param id 用户id
     * @return
     */
    @Trace
    @Override
    public User findById(Long id) {
        return userMapper.selectById(id);
    }
}
