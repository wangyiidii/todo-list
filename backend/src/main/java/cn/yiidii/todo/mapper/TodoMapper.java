package cn.yiidii.todo.mapper;

import cn.yiidii.todo.model.entity.Todo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * TodoMapper
 *
 * @author ed w
 * @since 1.0
 */
@Mapper
public interface TodoMapper extends BaseMapper<Todo> {
}
