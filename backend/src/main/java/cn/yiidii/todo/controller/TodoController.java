package cn.yiidii.todo.controller;

import cn.yiidii.todo.model.dto.SaveTodoDTO;
import cn.yiidii.todo.model.dto.TodoSearchDTO;
import cn.yiidii.todo.model.dto.TodoInfoDTO;
import cn.yiidii.todo.service.ITodoService;
import cn.yiidii.web.R;
import cn.yiidii.web.annotation.Update;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * TodoController
 *
 * @author ed w
 * @since 1.0
 */
@RestController
@RequestMapping("/todo")
@RequiredArgsConstructor
public class TodoController {

    private final ITodoService todoService;

    @GetMapping
    public R<List<TodoInfoDTO>> list(TodoSearchDTO todoSearchDTO) {
        return R.ok(todoService.list(todoSearchDTO));
    }


    @PostMapping
    public R<TodoInfoDTO> create(@RequestBody @Validated SaveTodoDTO saveTodoDTO) {
        return R.ok(todoService.create(saveTodoDTO), "添加成功");
    }

    @PutMapping
    public R<TodoInfoDTO> update(@RequestBody @Validated(Update.class) SaveTodoDTO saveTodoDTO) {
        return R.ok(todoService.update(saveTodoDTO), "修改成功");
    }

    @PutMapping("/{id}/complete")
    public R<TodoInfoDTO> complete(@PathVariable @NotNull(message = "id不能为空") Long id) {
        return R.ok(todoService.complete(id), "完成待办成功");
    }

    @PutMapping("/{id}/cancel")
    public R<TodoInfoDTO> cancel(@PathVariable @NotNull(message = "id不能为空") Long id) {
        return R.ok(todoService.cancel(id), "取消待办成功");
    }

    @PutMapping("/{id}/redo")
    public R<TodoInfoDTO> redo(@PathVariable @NotNull(message = "id不能为空") Long id) {
        return R.ok(todoService.redo(id), "取消待办成功");
    }
}
