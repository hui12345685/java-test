package org.example.config;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ObjectMapperConfiguration {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizerTest() {
        return builder -> {
            /*
启用 `WRITE_DATES_AS_TIMESTAMPS` 特性**：将日期类型作为时间戳（即以毫秒为单位的数值）写入 JSON。
禁用 `WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS` 和 `READ_DATE_TIMESTAMPS_AS_NANOSECONDS` 特性**：在写入和读取日期时间时，不使用纳秒级精度。
禁用 `FAIL_ON_UNKNOWN_PROPERTIES` 特性**：在解析 JSON 时，如果遇到未知属性，不会导致解析失败。
为 `LocalTime` 类型配置自定义序列化器**：使用指定的日期时间格式（`HH:mm:ss`）将 `LocalTime` 类型的值序列化为 JSON。
            */
            final DateTimeFormatter localDatTimeFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
            builder.featuresToEnable(WRITE_DATES_AS_TIMESTAMPS)
                    .featuresToDisable(WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS)
                    .featuresToDisable(READ_DATE_TIMESTAMPS_AS_NANOSECONDS)
                    .featuresToDisable(FAIL_ON_UNKNOWN_PROPERTIES)
                    .serializerByType(LocalTime.class, new LocalTimeSerializer(localDatTimeFormat));
        };
    }
}
