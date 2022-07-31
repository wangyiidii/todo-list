package cn.yiidii.todo.intercepter;

import cn.hutool.core.exceptions.ValidateException;
import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;
import cn.hutool.jwt.JWTValidator;
import cn.hutool.jwt.signers.JWTSignerUtil;
import cn.yiidii.boot.context.ContextUtil;
import cn.yiidii.todo.model.constant.Const;
import cn.yiidii.web.exception.BizException;
import cn.yiidii.web.exception.code.ExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.Date;

/**
 * LoginFilter
 *
 * @author ed w
 * @since 1.0
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    public final String[] IGNORE_URL = new String[]{"/oauth/**"};
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        AntPathMatcher antPathMatcher = new AntPathMatcher();
        boolean ignore = Arrays.stream(IGNORE_URL)
                .filter(p -> antPathMatcher.match(p, requestURI))
                .findAny()
                .isPresent();
        if (ignore) {
            return HandlerInterceptor.super.preHandle(request, response, handler);
        }

        String token = request.getHeader("token");
        try {
            JWT jwt = JWTUtil.parseToken(token);
            JWTValidator validator = JWTValidator.of(jwt);
            validator.validateAlgorithm(JWTSignerUtil.hs256(Const.JWT_KEY.getBytes()));
            validator.validateDate(new Date());

            // 设置userId上下文
            ContextUtil.setUserId(Long.parseLong(jwt.getPayload("id").toString()));
        } catch (Exception e) {
            log.error("LoginInterceptor ex: {}", e.getMessage());
            throw new BizException(ExceptionCode.JWT_NOT_LOGIN.getCode(), ExceptionCode.JWT_NOT_LOGIN.getMsg());
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
