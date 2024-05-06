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
    
    /*
        * 添加分类
     */
    @PostMapping("/add")
    public Result addCategory(@RequestBody @Validated(Category.add.class) Category category) {
        categoryService.addCategory(category);
        return Result.success();
    }
    
    /*
        * 查询分类列表
     */
    @GetMapping("list")
    public Result<List<Category>> list() {
        List<Category> categories = categoryService.list();
        return Result.success(categories);
    }
    
    /*
        * 删除分类
     */
    @GetMapping("/detail")
    public Result detail(Integer id) {
        return Result.success(categoryService.getById(id));
    }
    
    /*
        * 更新分类
     */
    @PutMapping("/update")
    public Result update(@RequestBody @Validated(Category.update.class) Category category) {
        categoryService.update(category);
        return Result.success();
    }
}
