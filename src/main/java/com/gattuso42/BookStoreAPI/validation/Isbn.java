package com.gattuso42.BookStoreAPI.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IsbnValidator.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Isbn {
    String message() default "Isbn format must be 13 digits";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
