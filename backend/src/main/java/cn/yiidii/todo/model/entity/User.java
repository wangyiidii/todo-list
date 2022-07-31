package cn.yiidii.todo.model.entity;

import cn.yiidii.mybatis.entity.Entity;
import lombok.Data;

/**
 * User
 *
 * @author ed w
 * @date 2022/7/29 16:46
 */
@Data
public class User extends Entity<Long> {
    private String uuid;
    private String username;
    private String nickname;
    private String source;
    private String avatar;
}
