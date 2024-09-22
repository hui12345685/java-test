package org.example.common.addname;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.time.Duration;
import org.example.service.GetNameService;

public class AddNameJacksonAnnotationIntrospector extends JacksonAnnotationIntrospector  {

    private final AnnotationIntrospector delegate;
    private final LoadingCache<String, String> cache;


    public AddNameJacksonAnnotationIntrospector(AnnotationIntrospector delegate,
            GetNameService GetNameService) {
        this.delegate = delegate;

        cache = Caffeine.newBuilder()
                .refreshAfterWrite(Duration.ofMinutes(10))
                .maximumSize(9999)
                .build(userid -> {
                    try {
                        final String name = GetNameService.getNameById(userid);
                        return name == null ? userid : name;
                    } catch (Exception e) {
                        return userid;
                    }
                });
    }

    // 重写了findSerializer，返回的时候会去找每一个字段的序列化器 (debug了下，看起来只有第一次会找，感觉是做了个缓存)
    // 如果有@AddName，则使用AddNameJsonSerializer，并调用相应的serialize
    @Override
    public Object findSerializer(Annotated annotated) {
        AddName addName = annotated.getAnnotation(AddName.class);
        if (addName != null) {
            // 只有加了AddName注解的，才使用AddNameJsonSerializer jackson序列化器
            return new AddNameJsonSerializer(cache, addName);
        }
        return delegate != null ? delegate.findSerializer(annotated): null;
    }
}
