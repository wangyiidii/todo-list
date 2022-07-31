package cn.yiidii.todo.model.enums;

import cn.yiidii.base.enumeration.Enumerator;
import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;

/**
 * 待办状态
 *
 * @author ed w
 * @since 1.0
 */
@AllArgsConstructor
public enum TodoStatus implements Enumerator {

    DOING(0, "进行中"),
    COMPLETED(10, "已完成"),
    CANCEL(20, "已取消");

    @EnumValue
    private int code;
    private String desc;

    @Override
    public Integer code() {
        return code;
    }

    @Override
    public String desc() {
        return desc;
    }
}
