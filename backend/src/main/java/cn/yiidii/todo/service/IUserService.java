package cn.yiidii.todo.service;

import cn.yiidii.todo.model.dto.UserInfoDTO;
import cn.yiidii.todo.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import me.zhyd.oauth.model.AuthUser;

/**
 * IUserService
 *
 * @author ed w
 * @date 2022/7/29 16:48
 */
public interface IUserService extends IService<User> {

    /**
     * 根据uuid 和 source获取用户
     *
     * @param uuid   uuid
     * @param source source
     * @return
     */
    UserInfoDTO getUserInfoByUUIDAndSource(String uuid, String source);

    UserInfoDTO createUser(AuthUser authUser);

}
