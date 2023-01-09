package pers.spectred.translate.observer.listener;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pers.spectred.translate.dto.YouDaoResponse;
import pers.spectred.translate.observer.event.TranslateSuccessEvent;
import pers.spectred.translate.util.SingletonObjectMapper;
import pers.spectred.translate.util.TranslateUtils;

@Slf4j
@Component
public class TranslateStoreListener implements ApplicationListener<TranslateSuccessEvent>, ITranslateListener {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Async
    @Override
    public void onApplicationEvent(TranslateSuccessEvent event) {
        YouDaoResponse source = (YouDaoResponse) event.getSource();
        String query = source.getQuery();
        String key = TranslateUtils.key(query);
        try {
            String s = SingletonObjectMapper.INSTANCE.get().writeValueAsString(source);
            stringRedisTemplate.opsForHash().put(key, query, s);
            log.debug("[Listener] store: {}", query);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
