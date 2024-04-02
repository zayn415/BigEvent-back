package com.zayn.bigevent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zayn.bigevent.pojo.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zayn
 * * @date 2024/3/31/23:45
 */
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {
    
    /**
     * 根据分类名称查询分类
     * @param categoryName 分类名称
     * @return 是否存在
     */
    @Select("select count(*) from category where category_name = #{categoryName}")
    boolean selectByName(String categoryName);
    
    /**
     * 根据用户id查询分类
     * @param userId 用户id
     * @return 分类列表
     */
    @Select("select * from category where create_user = #{userId}")
    List<Category> selectByUserId(Integer userId);
}
