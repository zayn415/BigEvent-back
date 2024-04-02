package com.zayn.bigevent.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zayn.bigevent.mapper.ArticleMapper;
import com.zayn.bigevent.pojo.Article;
import com.zayn.bigevent.pojo.PageBean;
import com.zayn.bigevent.pojo.Result;
import com.zayn.bigevent.service.ArticleService;
import com.zayn.bigevent.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author zayn
 * * @date 2024/4/1/20:06
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    
    /**
     * 添加文章
     * @param article 文章
     */
    @Override
    public void add(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        Map<String, Object> map = ThreadLocalUtil.get();
        article.setCreateUser((Integer) map.get("id"));
        articleMapper.insert(article);
    }
    
    /**
     * 更新文章
     * @param article 文章
     */
    @Override
    public void update(Article article) {
        article.setUpdateTime(LocalDateTime.now());
        articleMapper.updateById(article);
    }
    
    /**
     * 删除文章
     * @param id 文章id
     */
    @Override
    public void delete(Integer id) {
        articleMapper.deleteById(id);
    }
    
    /**
     * 获取文章
     * @param id 文章id
     * @return 文章
     */
    @Override
    public Article get(Integer id) {
        return articleMapper.selectById(id);
    }
    
    /**
     * 获取文章列表
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @param categoryId 分类id
     * @param state 文章状态
     * @return 文章列表
     */
    @Override
    public PageBean<Article> list(int pageNum, int pageSize, int categoryId, int state) {
        // 开启分页
        PageHelper.startPage(pageNum, pageSize);
        
        // 查询
        List<Article> articles = articleMapper.selectList(new QueryWrapper<Article>()
                .eq("category_id", categoryId)
                .eq("state", state));
        
        // 获取分页信息
        PageInfo<Article> pageInfo = new PageInfo<>(articles);
        
        // 封装数据并返回
        PageBean<Article> pb = new PageBean<>();
        pb.setTotal(pageInfo.getTotal());
        pb.setList(pageInfo.getList());
        return pb;
    }
    
}
