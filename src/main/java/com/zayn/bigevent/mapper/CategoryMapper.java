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
    
    @Select("select count(*) from category where category_name = #{categoryName}")
    boolean selectByName(String categoryName);
    
    @Select("select * from category where create_user = #{l}")
    List<Category> selectByUserId(long l);
}