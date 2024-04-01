package com.zayn.bigevent.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import java.time.LocalDateTime;

/**
 * @author zayn
 * * @date 2024/4/1/20:14
 */
@Data
@TableName("article")
public class Article {
    private Integer id;
    @NotEmpty
    private String title;
    @NotEmpty
    private String content;
    @URL
    private String coverImg;
    private int state;
    private Integer categoryId;
    private Integer createUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
