package cn.yiidii.todo.model.entity;

import cn.yiidii.mybatis.entity.Entity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * label
 *
 * @author ed w
 * @since 1.0
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Label extends Entity<Long> {
    private Long uid;
    private String color;
    private String name;
    private String remark;
}
