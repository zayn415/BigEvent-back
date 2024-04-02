package com.zayn.bigevent.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * @author zayn
 * * @date 2024/4/1/22:06
 */
public class StateValidation implements ConstraintValidator<State, Integer> {
    @Override
    public void initialize(State state) {
    }

    @Override
    public boolean isValid(Integer state, ConstraintValidatorContext cvc) {
        return state == 0 || state == 1;
    }
}
