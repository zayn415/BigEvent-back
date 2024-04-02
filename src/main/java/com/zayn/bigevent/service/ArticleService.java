package com.zayn.bigevent.service;

import com.zayn.bigevent.pojo.Article;
import com.zayn.bigevent.pojo.PageBean;

import java.util.List;

/**
 * @author zayn
 * * @date 2024/4/1/20:06
 */
public interface ArticleService {
    void add(Article article);
    
    PageBean<Article> list(int pageNum, int pageSize, int categoryId, int state);
    
    void update(Article article);
    
    void delete(Integer id);
    
    Article get(Integer id);
}
