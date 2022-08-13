package com.ly.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author YiMeng
 * @DateTime: 2022/8/12 15:58
 * @Description: TODO
 */
@Configuration
public class MallConfig {

    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper objectMapper =new ObjectMapper();
        return objectMapper;
    }
}
