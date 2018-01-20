package com.kasakad.fileio.kasakaidfileio.infrastructure.spring;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.kasakad.fileio.kasakaidfileio.infrastructure.Parser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

@Configuration
@EnableAspectJAutoProxy
public class BeanConfiguration {

    @Autowired
    private Parser parser;

    @Bean
    public Jackson2ObjectMapperBuilder objectMapperBuilder() {
        Jackson2ObjectMapperBuilder builder = new Jackson2ObjectMapperBuilder() {

            @Override
            public void configure(ObjectMapper objectMapper) {
                super.configure(objectMapper);
                // フィールドでシリアライズ/デシリアライズ
                objectMapper
                        .setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
            }
        };

        return builder.featuresToEnable(SerializationFeature.INDENT_OUTPUT) // JSON のシリアル化でインデントつける
                .featuresToDisable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS) // [2020, 0, 0] をやめる
                .featuresToDisable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES) // 存在しないプロパティのエラーは無視する。
                ;
    }
}
