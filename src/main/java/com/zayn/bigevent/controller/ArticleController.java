package com.zayn.bigevent.controller;

import com.zayn.bigevent.pojo.Article;
import com.zayn.bigevent.pojo.PageBean;
import com.zayn.bigevent.pojo.Result;
import com.zayn.bigevent.service.ArticleService;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author zayn
 * * @date 2024/4/1/20:05
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    
    /*
        * 添加文章
     */
    @PostMapping("/add")
    public Result add(@RequestBody @Validated Article article) {
        articleService.add(article);
        return new Result();
    }
    
    /*
        * 更新文章
     */
    @PutMapping("/update")
    public Result update(@RequestBody @Validated Article article) {
        articleService.update(article);
        return Result.success();
    }
    
    /*
        * 删除文章
     */
    @DeleteMapping("/delete")
    public Result delete(Integer id) {
        articleService.delete(id);
        return Result.success();
    }
    
    /*
        * 获取文章详情
     */
    @GetMapping("/detail")
    public Result<Article> get(Integer id) {
        Article article = articleService.get(id);
        return Result.success(article);
    }
    
    /*
        * 分页获取文章列表
     */
    @GetMapping("/list")
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) Integer state
    ) {
        PageBean<Article> pb = articleService.list(pageNum, pageSize, categoryId, state);
        return Result.success(pb);
    }
}
