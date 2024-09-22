package org.example.common.addname;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**注解的本质就是一些元数据，具体的实现要看再哪里用了这个元数据，在使用的地方根据元数据做一些操作
 * 如本注解就可以在返回的Response中添加一些字段
**/

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AddName {

    /**
     * 生成的属性名， 如不填， 默认为原属性名加 "_name"
     */
    String value() default "";

    /**
     * 是否把 username 复盖到原属性
     */
    boolean overwrite() default false;
}

