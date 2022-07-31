package cn.yiidii.todo.controller;

import cn.yiidii.todo.model.dto.AuthorizeRequestDTO;
import cn.yiidii.todo.model.dto.LoginSuccessDTO;
import cn.yiidii.todo.model.dto.UserInfoDTO;
import cn.yiidii.todo.service.IOauthService;
import cn.yiidii.web.R;
import com.alibaba.fastjson.JSONObject;
import com.xkcoding.justauth.AuthRequestFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.zhyd.oauth.model.AuthCallback;
import me.zhyd.oauth.request.AuthRequest;
import me.zhyd.oauth.utils.AuthStateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * OauthController
 *
 * @author ed w
 * @date 2022/7/29 16:34
 */
@Slf4j
@RestController
@RequestMapping("/oauth")
@RequiredArgsConstructor(onConstructor_ = @Autowired)
public class OauthController {

    private final AuthRequestFactory factory;
    private final IOauthService oauthService;

    @GetMapping
    public R<?> list() {
        return R.ok(factory.oauthList());
    }

    @GetMapping("/login/{type}")
    public void login(@PathVariable String type, HttpServletResponse response) throws IOException {
        AuthRequest authRequest = factory.get(type);
        response.sendRedirect(authRequest.authorize(AuthStateUtils.createState()));
    }

    @RequestMapping("/{type}/callback")
    public R<LoginSuccessDTO> login(@PathVariable String type, @RequestBody AuthorizeRequestDTO authorizeRequestDTO) {
        return R.ok(oauthService.login(type, authorizeRequestDTO));
    }
}
