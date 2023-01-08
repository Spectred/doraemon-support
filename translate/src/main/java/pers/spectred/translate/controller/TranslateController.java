package pers.spectred.translate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.spectred.translate.dto.YouDaoResponse;
import pers.spectred.translate.observer.event.TranslateSuccessEvent;
import pers.spectred.translate.tool.TranslateTools;

import java.util.*;
import java.util.stream.Collectors;

@RequestMapping("/translate")
@RestController
public class TranslateController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    public ApplicationEventPublisher applicationEventPublisher;

    @GetMapping
    public YouDaoResponse translate(@RequestParam("word") String word) {
        YouDaoResponse translate = TranslateTools.translate(word, 0);
        applicationEventPublisher.publishEvent(new TranslateSuccessEvent(translate));
        return translate;
    }

    @GetMapping("/rank")
    public List<?> rank(@RequestParam(value = "start", required = false, defaultValue = "0") long start,
                        @RequestParam(value = "end", required = false, defaultValue = "10") long end) {
        Set<ZSetOperations.TypedTuple<String>> typedTuples = stringRedisTemplate.opsForZSet().reverseRangeWithScores("word-rank", start, end);
        return typedTuples.stream().map(this::map).collect(Collectors.toList());
    }

    @GetMapping("/get")
    public int get(@RequestParam("word") String word) {
        Double score = stringRedisTemplate.opsForZSet().score("word-rank", word);
        return score.intValue();
    }

    private Map<String, Integer> map(ZSetOperations.TypedTuple<String> typedTuple) {
        Map<String, Integer> map = new HashMap<>();
        map.put(typedTuple.getValue(), typedTuple.getScore().intValue());
        return map;
    }
}
