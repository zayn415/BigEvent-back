package com.zayn.bigevent.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

/**
 * @author zayn
 * * @date 2024/3/26/21:43
 */
@Documented // 生成文档
@Target({ElementType.PARAMETER}) // 作用在参数上
@Retention(RetentionPolicy.RUNTIME) // 运行时生效
@Constraint(validatedBy = {PasswordValidator.class}) //关联校验器
public @interface Password {
    String message() default "Invalid password"; // 校验不通过时默认返回的信息
    Class<?>[] groups() default {}; // 分组
    Class<? extends Payload>[] payload() default {}; // 负载
}
