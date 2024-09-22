package org.example.common.addname;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.github.benmanes.caffeine.cache.LoadingCache;

import java.io.IOException;

/**spring boot自定义jackson序列化器。
具体调用时机:

直接序列化对象：当你直接将对象序列化为JSON字符串时，自定义序列化器会被调用。

序列化包含该对象的复杂对象：如果你序列化一个包含该对象的复杂对象（如列表、映射或其他自定义对象），自定义序列化器也会被调用。

 具体函数如：writeValueAsString，writeValueAsBytes，writeValue，write 等方法。
 **/

public class AddNameJsonSerializer extends JsonSerializer<String> {

    private final AddName addName;
    public final LoadingCache<String, String> cache;

    AddNameJsonSerializer(LoadingCache<String, String> cache, AddName extendUserName) {
        this.cache = cache;
        this.addName = extendUserName;
    }

    /**
     * 重写了serialize，在http请求返回的时候，会对每一个字段调用serialize，如果findSerializer返回了AddNameJsonSerializer
     * 则对应的字段调用serialize的时候会走到这里
     * 所以这里的功能是给加了@AddName的注解，自动加上xx_name字段
    **/
    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        if(addName.overwrite()){
            gen.writeString(cache.get(value));
        }else{
            gen.writeString(value);
            String nameField = addName.value();
            if(nameField == null || nameField.isEmpty()){
                //如果没有设置注解对应的值，则添加的字段为当前字段+_name
                nameField = gen.getOutputContext().getCurrentName() + "_name";
            }
            gen.writeFieldName(nameField);
            gen.writeString(cache.get(value));
        }
    }
}
