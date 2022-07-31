package cn.yiidii.todo.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * 用户信息
 *
 * @author ed w
 * @since 1.0
 */
@Data
public class UserInfoDTO {
    private Long id;
    private String uuid;
    private String username;
    private String nickname;
    private String source;
    private String avatar;
    private LocalDateTime createTime;
}
