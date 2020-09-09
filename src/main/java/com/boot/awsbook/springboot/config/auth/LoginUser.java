package com.boot.awsbook.springboot.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER)   // where this annotation can be used
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginUser {  // annotation class
}
