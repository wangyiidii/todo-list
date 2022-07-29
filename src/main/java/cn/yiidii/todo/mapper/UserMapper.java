package cn.yiidii.todo.mapper;

import cn.yiidii.todo.model.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * UserMapper
 *
 * @author ed w
 * @date 2022/7/29 16:45
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
