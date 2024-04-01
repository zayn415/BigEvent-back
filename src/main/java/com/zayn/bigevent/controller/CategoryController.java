package com.zayn.bigevent.controller;

import com.zayn.bigevent.pojo.Category;
import com.zayn.bigevent.pojo.Result;
import com.zayn.bigevent.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    
    @GetMapping("list")
    public Result<List<Category>> list() {
        List<Category> categories = categoryService.list();
        return Result.success(categories);
    }
}
