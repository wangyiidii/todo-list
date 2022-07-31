package cn.yiidii.todo.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.jwt.JWT;
import cn.yiidii.todo.model.constant.Const;
import cn.yiidii.todo.model.dto.AuthorizeRequestDTO;
import cn.yiidii.todo.model.dto.LoginSuccessDTO;
import cn.yiidii.todo.model.dto.UserInfoDTO;
import cn.yiidii.todo.service.IOauthService;
import cn.yiidii.todo.service.IUserService;
import cn.yiidii.web.exception.BizException;
import com.xkcoding.justauth.AuthRequestFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.model.AuthResponse;
import me.zhyd.oauth.model.AuthUser;
import me.zhyd.oauth.request.AuthRequest;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;

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
    public LoginSuccessDTO login(String type, AuthorizeRequestDTO authorizeRequestDTO) {
        AuthRequest authRequest = factory.get(type);

        AuthCallback authCallback = new AuthCallback();
        authCallback.setCode(authorizeRequestDTO.getCode());
        authCallback.setState(authorizeRequestDTO.getState());

        AuthResponse<AuthUser> response = authRequest.login(authCallback);
        if (response.getCode() != 2000) {
            throw new BizException(response.getMsg());
        }

        AuthUser authUser = response.getData();
        UserInfoDTO userInfo = userService.getUserInfoByUUIDAndSource(authUser.getUuid(), authUser.getSource());
        if (Objects.isNull(userInfo)) {
            userInfo = userService.createUser(authUser);
        }

        String token = JWT.create()
                .setPayload("id", userInfo.getId())
                .setPayload("name", userInfo.getUsername())
                .setKey(Const.JWT_KEY.getBytes(StandardCharsets.UTF_8))
                .setExpiresAt(DateUtil.offsetDay(new Date(), 7))
                .sign();
        return new LoginSuccessDTO(token, userInfo);
    }
}
