package com.itsun.stock.config;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.itsun.stock.pojo.vo.StockInfoConfig;
import com.itsun.stock.utils.IdWorker;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableConfigurationProperties(StockInfoConfig.class)//创建该参数的bean
@Configuration
public class CommonConfig {


    /**
     * 基于雪花算法 生成sessionId
     *
     * @return 机器Id,序列化
     */
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1L,2L);
    }
    //配置Long的序列化
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        SimpleModule simpleModule = new SimpleModule();

        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE,ToStringSerializer.instance);

        objectMapper.registerModule(simpleModule);
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
        return mappingJackson2HttpMessageConverter;
    }


    /**
     *
     * @return
     */
    @Bean
    public Cache<String,Object> caffeineCache(){
        Cache<String, Object> cache = Caffeine
                .newBuilder()
                .maximumSize(200)//设置缓存数量上限
//                .expireAfterAccess(1, TimeUnit.SECONDS)//访问1秒后删除
//                .expireAfterWrite(1,TimeUnit.SECONDS)//写入1秒后删除
                .initialCapacity(100)// 初始的缓存空间大小
                .recordStats()//开启统计
                .build();
        return cache;
    }
}
