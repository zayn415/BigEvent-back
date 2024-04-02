package com.zayn.bigevent.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * @author zayn
 * * @date 2024/4/1/22:06
 */

@Documented // 生成文档
@Target({ElementType.FIELD}) // 作用在字段上
@Retention(RetentionPolicy.RUNTIME) // 运行时生效
@Constraint(validatedBy = {StateValidation.class}) //关联校验器
public @interface State {
    String message() default "Invalid state"; // 校验不通过时默认返回的信息
    
    Class<?>[] groups() default {}; // 分组
    
    Class<? extends Payload>[] payload() default {}; // 负载
}
