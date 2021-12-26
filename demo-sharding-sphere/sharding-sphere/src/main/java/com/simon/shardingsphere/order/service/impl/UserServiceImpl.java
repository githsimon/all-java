package com.simon.shardingsphere.order.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.simon.shardingsphere.order.domain.User;
import com.simon.shardingsphere.order.service.UserService;
import com.simon.shardingsphere.order.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author simon
* @description 针对表【t_user】的数据库操作Service实现
* @createDate 2021-12-18 19:35:21
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




