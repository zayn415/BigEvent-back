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
        category.setCreateUser((Integer) map.get("id"));
        categoryMapper.insert(category);
    }
    
    /**
     * 查询分类列表
     * @return 分类列表
     */
    @Override
    public List<Category> list() {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer id = (Integer) claims.get("id");
        return categoryMapper.selectByUserId(id);
    }
    
    /**
     * 根据id删除分类
     * @param id 分类id
     * @return 是否删除成功
     */
    @Override
    public Object getById(Integer id) {
        return categoryMapper.selectById(id);
    }
    
    /**
     * 更新分类信息
     * @param category 更新后的分类信息
     */
    @Override
    public void update(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.updateById(category);
    }
}
