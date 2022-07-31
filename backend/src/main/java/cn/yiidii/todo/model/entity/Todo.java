package cn.yiidii.todo.model.entity;

import cn.yiidii.mybatis.entity.Entity;
import cn.yiidii.todo.model.enums.TodoStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * 待办事项
 *
 * @author ed w
 * @since 1.0
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Todo extends Entity<Long> {
    private Long uid;
    private String content;
    private String remark;
    private LocalDateTime completeTime;
    private LocalDateTime cancelTime;
    private TodoStatus todoStatus;

    public Todo(Long id, TodoStatus todoStatus){
        this.id = id;
        this.todoStatus = todoStatus;
    }
}
