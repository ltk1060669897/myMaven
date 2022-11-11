package com.example.boot.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author ltk
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = LengthValidate.class)
public @interface Length {
    //最小长度
    int min() default 2;
    //最大长度
    int max() default 5;
    //错误信息
    String message() default "length is not right";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
