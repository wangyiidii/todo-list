package cn.yiidii.todo.model.dto;

import cn.yiidii.todo.model.enums.TodoStatus;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * TodoInfoDTO
 *
 * @author ed w
 * @since 1.0
 */
@Data
public class TodoInfoDTO {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    private Long uid;
    private String content;
    private String remark;
    private LocalDateTime completeTime;
    private LocalDateTime cancelTime;
    private TodoStatus todoStatus;
    private LocalDateTime createTime;
}
