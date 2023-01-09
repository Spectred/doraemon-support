package pers.spectred.translate.observer.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pers.spectred.translate.dto.YouDaoResponse;
import pers.spectred.translate.observer.event.TranslateSuccessEvent;
import pers.spectred.translate.util.TranslateUtils;

@Slf4j
@Component
public class TranslateRankListener implements ApplicationListener<TranslateSuccessEvent>, ITranslateListener {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Async
    @Override
    public void onApplicationEvent(TranslateSuccessEvent event) {
        YouDaoResponse source = (YouDaoResponse) event.getSource();
        String query = source.getQuery();
        String key = TranslateUtils.WORD_RANK;
        stringRedisTemplate.opsForZSet().incrementScore(key, query, 1D);
        log.debug("[Listener] rank: {}", query);
    }
}
