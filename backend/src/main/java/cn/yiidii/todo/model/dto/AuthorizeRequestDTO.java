package cn.yiidii.todo.model.dto;

import lombok.Data;

/**
 * AuthorizeRequestDTO
 *
 * @author ed w
 * @since 1.0
 */
@Data
public class AuthorizeRequestDTO {
    private String state;
    private String code;
}
