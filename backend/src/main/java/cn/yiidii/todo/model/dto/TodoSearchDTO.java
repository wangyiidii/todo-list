package cn.yiidii.todo.model.dto;

import cn.yiidii.todo.model.enums.TodoStatus;
import cn.yiidii.web.BaseSearchDTO;
import lombok.Data;

/**
 * SearchDTO
 *
 * @author ed w
 * @since 1.0
 */
@Data
public class TodoSearchDTO extends BaseSearchDTO {
    private Integer todoStatus;
}
