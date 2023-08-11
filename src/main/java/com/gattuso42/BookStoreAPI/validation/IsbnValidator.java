package com.gattuso42.BookStoreAPI.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class IsbnValidator implements ConstraintValidator<Isbn,String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        return s.length() == 13 ;
    }
}
