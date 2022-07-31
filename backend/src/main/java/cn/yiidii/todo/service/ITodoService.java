package cn.yiidii.todo.service;

import cn.yiidii.todo.model.dto.SaveTodoDTO;
import cn.yiidii.todo.model.dto.TodoSearchDTO;
import cn.yiidii.todo.model.dto.TodoInfoDTO;
import cn.yiidii.todo.model.entity.Todo;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * IUserService
 *
 * @author ed w
 * @since 1.0
 */
public interface ITodoService extends IService<Todo> {

    List<TodoInfoDTO> list(TodoSearchDTO todoSearchDTO);

    /**
     * 创建待办
     *
     * @param saveTodoDTO saveTodoDTO
     * @return TodoInfoDTO
     */
    TodoInfoDTO create(SaveTodoDTO saveTodoDTO);

    /**
     * 更新待办
     *
     * @param saveTodoDTO saveTodoDTO
     * @return TodoInfoDTO
     */
    TodoInfoDTO update(SaveTodoDTO saveTodoDTO);

    /**
     * 完成代办
     *
     * @param id id
     * @return TodoInfoDTO
     */
    TodoInfoDTO complete(Long id);

    /**
     * 取消待办
     *
     * @param id    id
     * @return
     */
    TodoInfoDTO cancel(Long id);

    TodoInfoDTO redo(Long id);
}
