package cn.yiidii.todo.controller;

import cn.yiidii.todo.service.ILabelService;
import cn.yiidii.web.R;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TagController
 *
 * @author ed w
 * @since 1.0
 */
@RestController
@RequestMapping("/label")
@RequiredArgsConstructor
public class LabelController {

    private final ILabelService labelService;

    @GetMapping
    public R<?> list() {
        return R.ok(labelService.list());
    }
}
