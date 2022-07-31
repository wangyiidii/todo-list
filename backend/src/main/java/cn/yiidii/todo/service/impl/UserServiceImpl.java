package cn.yiidii.todo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.yiidii.todo.mapper.UserMapper;
import cn.yiidii.todo.model.dto.UserInfoDTO;
import cn.yiidii.todo.model.entity.User;
import cn.yiidii.todo.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.zhyd.oauth.model.AuthUser;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * UserServiceImpl
 *
 * @author ed w
 * @date 2022/7/29 16:49
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {


    @Override
    public UserInfoDTO getUserInfoByUUIDAndSource(String uuid, String source) {
        User user = lambdaQuery().select(User::getId, User::getUuid, User::getSource, User::getUsername, User::getNickname, User::getAvatar, User::getCreateTime)
                .eq(User::getUuid, uuid)
                .eq(User::getSource, source)
                .one();
        if (Objects.nonNull(user)) {
            return BeanUtil.toBean(user, UserInfoDTO.class);
        }
        return null;
    }

    @Override
    public UserInfoDTO createUser(AuthUser authUser) {
        if (Objects.isNull(authUser)) {
            throw new IllegalArgumentException("authUser cannot be null");
        }
        User user = BeanUtil.toBean(authUser, User.class);
        this.save(user);
        return BeanUtil.toBean(user, UserInfoDTO.class);
    }
}
