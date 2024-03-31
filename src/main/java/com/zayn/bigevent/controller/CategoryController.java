package com.zayn.bigevent.controller;

import com.zayn.bigevent.pojo.Category;
import com.zayn.bigevent.pojo.Result;
import com.zayn.bigevent.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zayn
 * * @date 2024/3/31/23:42
 */

@Validated
@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    
    @PostMapping("/add")
    public Result addCategory(@RequestBody @Validated Category category) {
        categoryService.addCategory(category);
        return Result.success();
    }
}
