package org.example.common.addname;

import org.example.service.GetNameService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@AutoConfiguration
public class AddNameAutoConfiguration {

    /**GetNameService参数在spring boot启动的时候回去找对应的Bean，是个单例，全局一个
     * @AutoConfiguration + @Bean 可以是的启动的时候调用 userNameObjectMapperBuilderCustomizer
     * 如果需要将对应的注解给到第三方使用，则需要在文件
     * main/resources/META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
     * 中加上对应的类的全路径
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer userNameObjectMapperBuilderCustomizer(
            GetNameService GetNameService){
        return new Jackson2ObjectMapperBuilderCustomizer() {
            @Override
            public void customize(Jackson2ObjectMapperBuilder jacksonObjectMapperBuilder) {
                jacksonObjectMapperBuilder.annotationIntrospector(annotationIntrospector -> {
                    return new AddNameJacksonAnnotationIntrospector(annotationIntrospector, GetNameService);
                });
            }
        };
    }
}

