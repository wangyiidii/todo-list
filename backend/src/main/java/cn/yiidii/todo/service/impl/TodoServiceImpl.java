package cn.yiidii.todo.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import cn.yiidii.boot.context.ContextUtil;
import cn.yiidii.todo.mapper.TodoMapper;
import cn.yiidii.todo.model.dto.SaveTodoDTO;
import cn.yiidii.todo.model.dto.TodoSearchDTO;
import cn.yiidii.todo.model.dto.TodoInfoDTO;
import cn.yiidii.todo.model.entity.Todo;
import cn.yiidii.todo.model.enums.TodoStatus;
import cn.yiidii.todo.service.ITodoService;
import cn.yiidii.web.exception.BizException;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * TodoServiceImpl
 *
 * @author ed w
 * @since 1.0
 */
@Service
public class TodoServiceImpl extends ServiceImpl<TodoMapper, Todo> implements ITodoService {

    @Override
    public List<TodoInfoDTO> list(TodoSearchDTO todoSearchDTO) {
        LambdaQueryChainWrapper<Todo> lambdaQuery = this.lambdaQuery()
                .eq(Todo::getUid, ContextUtil.getUserId());

        Integer todoStatus = todoSearchDTO.getTodoStatus();
        if (Objects.nonNull(todoStatus)) {
            lambdaQuery = lambdaQuery.eq(Todo::getTodoStatus, todoSearchDTO.getTodoStatus());
        }
        return lambdaQuery
                .list()
                .stream()
                .map(t -> BeanUtil.toBean(t, TodoInfoDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public TodoInfoDTO create(SaveTodoDTO saveTodoDTO) {
        Todo todo = BeanUtil.toBean(saveTodoDTO, Todo.class);
        todo.setUid(ContextUtil.getUserId());
        this.save(todo);
        return BeanUtil.toBean(todo, TodoInfoDTO.class);
    }

    @Override
    public TodoInfoDTO update(SaveTodoDTO saveTodoDTO) {
        Todo todo = BeanUtil.toBean(saveTodoDTO, Todo.class);
        this.updateById(todo);
        return BeanUtil.toBean(todo, TodoInfoDTO.class);
    }

    @Override
    public TodoInfoDTO complete(Long id) {
        Todo todo = this.getById(id);
        if (Objects.isNull(todo)) {
            throw new BizException("待办不存在");
        }

        if (!todo.getTodoStatus().equals(TodoStatus.DOING)) {
            throw new BizException(StrUtil.format("待办状态不正确，当前状态为：{}", todo.getTodoStatus().desc()));
        }

        todo = new Todo(id, TodoStatus.COMPLETED);
        todo.setCompleteTime(LocalDateTime.now());
        this.updateById(todo);
        return BeanUtil.toBean(this.getById(id), TodoInfoDTO.class);
    }

    @Override
    public TodoInfoDTO cancel(Long id) {
        Todo todo = this.getById(id);
        if (Objects.isNull(todo)) {
            throw new BizException("待办不存在");
        }

        if (!todo.getTodoStatus().equals(TodoStatus.DOING)) {
            throw new BizException(StrUtil.format("待办状态不正确，当前状态为：{}", todo.getTodoStatus().desc()));
        }

        todo = new Todo(id, TodoStatus.CANCEL);
        todo.setCancelTime(LocalDateTime.now());
        this.updateById(todo);
        return BeanUtil.toBean(this.getById(id), TodoInfoDTO.class);
    }

    @Override
    public TodoInfoDTO redo(Long id) {
        Todo todo = this.getById(id);
        if (Objects.isNull(todo)) {
            throw new BizException("待办不存在");
        }

        if (!todo.getTodoStatus().equals(TodoStatus.CANCEL)) {
            throw new BizException(StrUtil.format("待办状态不正确，当前状态为：{}", todo.getTodoStatus().desc()));
        }

        todo = new Todo(id, TodoStatus.DOING);
        todo.setCancelTime(LocalDateTimeUtil.of(0));
        this.updateById(todo);
        return BeanUtil.toBean(this.getById(id), TodoInfoDTO.class);
    }
}
