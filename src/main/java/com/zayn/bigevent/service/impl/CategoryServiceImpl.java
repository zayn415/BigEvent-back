package com.zayn.bigevent.service.impl;

import com.zayn.bigevent.mapper.CategoryMapper;
import com.zayn.bigevent.pojo.Category;
import com.zayn.bigevent.service.CategoryService;
import com.zayn.bigevent.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author zayn
 * * @date 2024/3/31/23:46
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    
    /**
     * 添加分类，检验是否重名
     * @param category 分类
     */
    @Override
    public void addCategory(Category category) {
        if (categoryMapper.selectByName(category.getCategoryName())) {
            throw new RuntimeException("分类名称已存在");
        }
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        Map<String, Object> map = ThreadLocalUtil.get();
        category.setCreateUser((Long) map.get("id"));
        categoryMapper.insert(category);
    }
    
    @Override
    public List<Category> list() {
        Map<String, Object> claims = ThreadLocalUtil.get();
        String id = claims.get("id").toString();
        return categoryMapper.selectByUserId(Long.parseLong(id));
    }
}
