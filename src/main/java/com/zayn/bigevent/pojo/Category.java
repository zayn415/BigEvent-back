package com.zayn.bigevent.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author zayn
 * * @date 2024/3/31/23:44
 */
@Data
@TableName("category")
public class Category {
//    @NotNull
    private Integer id;
    @NotEmpty(message = "分类名称不能为空")
    private String categoryName;
//    @NotEmpty(message = "创建者不能为空")
    private Integer createUser;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;
}
