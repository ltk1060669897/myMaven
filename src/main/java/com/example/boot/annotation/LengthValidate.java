package com.example.boot.annotation;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author ltk
 */
public class LengthValidate implements ConstraintValidator<Length,String> {

    private int max;
    private int min;

    @Override
    public void initialize(Length length) {
        this.max = length.max();
        this.min = length.min();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(!StringUtils.isEmpty(s) && s.trim().length() <= max && s.trim().length() >= min){
            return true;
        }
        return false;
    }
}
