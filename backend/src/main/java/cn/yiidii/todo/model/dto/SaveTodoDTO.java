package cn.yiidii.todo.model.dto;

import cn.yiidii.web.annotation.Update;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * CreateTodoDTO
 *
 * @author ed w
 * @since 1.0
 */
@Data
public class SaveTodoDTO {
    @NotNull(message = "id不能为空", groups = Update.class)
    private Long id;
    @NotBlank(message = "")
    @Length(max = 255, message = "内容超长")
    private String content;
    @Length(max = 255, message = "备注超长")
    private String remark;
}
