package pers.spectred.translate.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pers.spectred.translate.dto.YouDaoResponse;
import pers.spectred.translate.tool.TranslateTools;

@RequestMapping("/translate")
@RestController
public class TranslateController {

    @GetMapping
    public YouDaoResponse translate(@RequestParam("word") String word) {
        YouDaoResponse translate = TranslateTools.translate(word, 0);
        return translate;
    }
}
