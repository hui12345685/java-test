package org.example.aspect;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RdLock {
    /**
     * key
     */
    String key()  default "";

    /**
     * 超时时间，默认10秒
     */
    int keyExpireTime() default 10;
}
