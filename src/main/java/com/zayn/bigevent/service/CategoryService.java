package com.zayn.bigevent.service;

import com.zayn.bigevent.pojo.Category;

import java.util.List;

/**
 * @author zayn
 * * @date 2024/3/31/23:45
 */
public interface CategoryService {
    void addCategory(Category category);
    
    List<Category> list();
}
