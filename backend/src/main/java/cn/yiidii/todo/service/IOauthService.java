package cn.yiidii.todo.service;

import cn.yiidii.todo.model.dto.AuthorizeRequestDTO;
import cn.yiidii.todo.model.dto.LoginSuccessDTO;
import cn.yiidii.todo.model.dto.UserInfoDTO;
import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.model.AuthCallback;

/**
 * OAuthService
 *
 * @author ed w
 * @date 2022/7/29 16:50
 */
public interface IOauthService {

    LoginSuccessDTO login(String type, AuthorizeRequestDTO authorizeRequestDTO);

}
