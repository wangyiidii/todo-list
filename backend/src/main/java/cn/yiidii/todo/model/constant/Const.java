package cn.yiidii.todo.model.constant;

import cn.hutool.core.util.RandomUtil;

/**
 * 常量
 *
 * @author ed w
 * @since 1.0
 */
public interface Const {

    String JWT_KEY = RandomUtil.randomString(16);

}
