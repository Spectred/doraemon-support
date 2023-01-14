package pers.spectred.translate.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * ObjectMapper单例
 *
 * @author spectred
 */
public enum SingletonObjectMapper {
    /**
     *
     */
    INSTANCE(get());
    
    private final ObjectMapper objectMapper;

    SingletonObjectMappper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    private static ObjectMapper get() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return objectMapper;
    }
    
    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
