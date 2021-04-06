package com.cnbg.zs.ebook.core.config;




import com.cnbg.zs.ebook.core.xss.XssFilter;
import com.cnbg.zs.ebook.core.xss.XssStringJsonSerializer;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author Faye.Wang
* @version 1.0
* @date 2020/9/4 23:55
* @Description
*/
@Configuration
@ConditionalOnClass(Jackson2ObjectMapperBuilder.class)
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer(){
        Jackson2ObjectMapperBuilderCustomizer customizer =  jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.serializerByType(Long.class, ToStringSerializer.instance)
        .serializerByType(Long.TYPE,ToStringSerializer.instance);
        return customizer;
    }
    @Bean
    public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter(){
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        //设置日期格式
        ObjectMapper objectMapper = new ObjectMapper();
        SimpleDateFormat smt = new SimpleDateFormat("yyyy-MM-dd");
        objectMapper.setDateFormat(smt);

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);
        objectMapper.registerModule(simpleModule);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);

        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(getSupportedMediaTypes());
        return mappingJackson2HttpMessageConverter;
    }
    public List<MediaType> getSupportedMediaTypes() {
        List<MediaType> supportedMediaTypes = new ArrayList<>();
                supportedMediaTypes.add(MediaType.APPLICATION_JSON);
                supportedMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
                supportedMediaTypes.add(MediaType.APPLICATION_ATOM_XML);
                supportedMediaTypes.add(MediaType.APPLICATION_FORM_URLENCODED);
                supportedMediaTypes.add(MediaType.APPLICATION_OCTET_STREAM);
                supportedMediaTypes.add(MediaType.APPLICATION_PDF);
                supportedMediaTypes.add(MediaType.APPLICATION_RSS_XML);
                supportedMediaTypes.add(MediaType.APPLICATION_XHTML_XML);
                supportedMediaTypes.add(MediaType.APPLICATION_XML);
                supportedMediaTypes.add(MediaType.IMAGE_GIF);
                supportedMediaTypes.add(MediaType.IMAGE_JPEG);
                supportedMediaTypes.add(MediaType.IMAGE_PNG);
                supportedMediaTypes.add(MediaType.TEXT_EVENT_STREAM);
                supportedMediaTypes.add(MediaType.TEXT_HTML);
                supportedMediaTypes.add(MediaType.TEXT_MARKDOWN);
                supportedMediaTypes.add(MediaType.TEXT_PLAIN);
                supportedMediaTypes.add(MediaType.TEXT_XML);
                supportedMediaTypes.add(MediaType.ALL);
                return supportedMediaTypes;
            }



            /**
            * xss过滤拦截器
            */
            @Bean
            public FilterRegistrationBean xssFilterRegistrationBean() {
                FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
                filterRegistrationBean.setFilter(new XssFilter());
                filterRegistrationBean.setOrder(2);
                filterRegistrationBean.setEnabled(true);
                filterRegistrationBean.addUrlPatterns("/*");
                Map<String, String> initParameters = new HashMap<>();
                initParameters.put("excludes", "/favicon.ico,/img/*,/js/*,/css/*");
                //initParameters.put("isIncludeRichText", "true");
                filterRegistrationBean.setInitParameters(initParameters);
                return filterRegistrationBean;
            }
            @Bean
            @Primary
            @ConditionalOnMissingBean(ObjectMapper.class)
            public ObjectMapper xssObjectMapper(Jackson2ObjectMapperBuilder builder) {
                //解析器
                ObjectMapper objectMapper = builder.createXmlMapper(false).build();
                //注册xss解析器
                SimpleModule xssModule = new SimpleModule("XssStringJsonSerializer");
                xssModule.addSerializer(new XssStringJsonSerializer());
                objectMapper.registerModule(xssModule);
                //返回
                return objectMapper;
            }


    }
