package pers.spectred.translate.observer.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pers.spectred.translate.dto.YouDaoResponse;
import pers.spectred.translate.observer.event.TranslateSuccessEvent;
import pers.spectred.translate.util.SingletonObjectMapper;

@Component
public class TranslateSuccessStoreListener implements ApplicationListener<TranslateSuccessEvent> {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Async
    @Override
    public void onApplicationEvent(TranslateSuccessEvent event) {
        YouDaoResponse source = (YouDaoResponse) event.getSource();
        System.out.println(Thread.currentThread().getName() + " -TranslateSuccessStoreListener");
        String query = source.getQuery();
        String key = key(query);
        try {
            String s = SingletonObjectMapper.INSTANCE.get().writeValueAsString(source);
            stringRedisTemplate.opsForHash().put(key, query, s);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String key(String query) {
        int s = query.charAt(0) - 'a';
        return "word:" + s;
    }
}
