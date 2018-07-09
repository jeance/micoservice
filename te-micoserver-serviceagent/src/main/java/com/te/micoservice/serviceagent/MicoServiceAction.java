package com.te.micoservice.serviceagent;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by cxj4842 on 2018/6/30.
 */

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MicoServiceAction  {
    String value() default "";
}
