package pers.spectred.translate.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import pers.spectred.translate.util.SingletonObjectMapper;

@Configuration(proxyBeanMethods = false)
public class MessageConverterConfiguration {


    @Bean
    public HttpMessageConverters httpMessageConverters() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(SingletonObjectMapper.INSTANCE.get());
        converter.setPrettyPrint(true);
        return new HttpMessageConverters(converter);
    }
}
