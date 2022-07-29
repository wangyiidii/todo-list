package cn.yiidii.todo.service;

import com.alibaba.fastjson.JSONObject;
import me.zhyd.oauth.model.AuthCallback;

/**
 * OAuthService
 *
 * @author ed w
 * @date 2022/7/29 16:50
 */
public interface IOauthService {

    JSONObject login(String type, AuthCallback callback);

}
