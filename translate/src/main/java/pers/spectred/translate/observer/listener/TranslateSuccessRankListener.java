package pers.spectred.translate.observer.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import pers.spectred.translate.dto.YouDaoResponse;
import pers.spectred.translate.observer.event.TranslateSuccessEvent;

@Component
public class TranslateSuccessRankListener implements ApplicationListener<TranslateSuccessEvent> {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Async
    @Override
    public void onApplicationEvent(TranslateSuccessEvent event) {
        YouDaoResponse source = (YouDaoResponse)event.getSource();
        stringRedisTemplate.opsForZSet().incrementScore("word-rank", source.getQuery(), 1D);
        System.out.println(Thread.currentThread().getName()+" -TranslateSuccessRankListener");
    }
}
