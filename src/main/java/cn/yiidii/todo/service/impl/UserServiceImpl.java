package cn.yiidii.todo.service.impl;

import cn.yiidii.todo.mapper.UserMapper;
import cn.yiidii.todo.model.entity.User;
import cn.yiidii.todo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * UserServiceImpl
 *
 * @author ed w
 * @date 2022/7/29 16:49
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
}
