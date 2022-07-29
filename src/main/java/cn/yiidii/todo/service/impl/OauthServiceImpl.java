package cn.yiidii.todo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.yiidii.todo.model.entity.User;
import cn.yiidii.todo.service.IOauthService;
import cn.yiidii.todo.service.IUserService;
import cn.yiidii.web.exception.BizException;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.xkcoding.justauth.AuthRequestFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.request.AuthRequest;
import org.springframework.stereotype.Service;

/**
 * OauthserviceImpl
 *
 * @author ed w
 * @date 2022/7/29 16:50
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class OauthServiceImpl implements IOauthService {

    private final AuthRequestFactory factory;
    private final IUserService userService;

    @Override
    public JSONObject login(String type, AuthCallback callback) {
        AuthRequest authRequest = factory.get(type);
        AuthResponse response = authRequest.login(callback);
        if (response.getCode() != 2000) {
            throw new BizException(response.getMsg());
        }
        User user = BeanUtil.toBean(response.getData(), User.class);
        userService.save(user);
        return JSONObject.parseObject(JSONObject.toJSONString(user));
    }
}
