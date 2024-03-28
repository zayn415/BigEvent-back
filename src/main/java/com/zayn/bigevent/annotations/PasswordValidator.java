package com.zayn.bigevent.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

/**
 * @author zayn
 * * @date 2024/3/26/21:49
 */
@Component
public class PasswordValidator implements ConstraintValidator<Password, String> {
    @Override
    public void initialize(Password password) {
    }
    
    /**
     * 验证密码是否符合规范
      * @param password 密码
     * @param cvc 验证上下文
     * @return 符合结果
     */
    @Override
    public boolean isValid(String password, ConstraintValidatorContext cvc) {
        return password != null &&
                password.matches("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$");
    }
}
