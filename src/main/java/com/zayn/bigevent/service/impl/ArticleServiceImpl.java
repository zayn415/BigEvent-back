package com.zayn.bigevent.service.impl;

import com.zayn.bigevent.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zayn
 * * @date 2024/4/1/20:06
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleService articleService;
}
