package cn.yiidii.todo.service.impl;

import cn.yiidii.todo.mapper.LabelMapper;
import cn.yiidii.todo.mapper.TodoMapper;
import cn.yiidii.todo.model.entity.Label;
import cn.yiidii.todo.model.entity.Todo;
import cn.yiidii.todo.service.ILabelService;
import cn.yiidii.todo.service.ITodoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * LabelServiceImpl
 *
 * @author ed w
 * @since 1.0
 */
@Service
public class LabelServiceImpl extends ServiceImpl<LabelMapper, Label> implements ILabelService {


}
