package com.zayn.bigevent.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zayn.bigevent.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author zayn
 * * @date 2024/4/1/20:05
 */
@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    
    /**
     * 根据用户id查询文章列表
     * @param id 用户id
     * @return 文章列表
     */
    @Select("select * from article where create_user = #{id}")
    List<Article> selectListByUserId(Integer id);
}
