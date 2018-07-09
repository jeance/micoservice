package com.te.micoservice.serviceagent;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by cxj4842 on 2018/6/28.
 */

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Component
@Documented
public @interface MicoService {
    String value() default "";
}


