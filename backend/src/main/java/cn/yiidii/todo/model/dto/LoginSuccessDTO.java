package cn.yiidii.todo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * LoginSuccessDTO
 *
 * @author ed w
 * @since 1.0
 */
@Data
@AllArgsConstructor
public class LoginSuccessDTO {
    private String token;
    private UserInfoDTO userInfo;
}
