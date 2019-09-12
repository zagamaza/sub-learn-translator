package ru.zagamaza.translator.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.zagamaza.translator.dto.Lang;
import ru.zagamaza.translator.dto.WordDto;
import ru.zagamaza.translator.service.SubService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/translator")
public class Controller {

    private final SubService subService;

    @PostMapping
    public List<WordDto> translate(@RequestBody List<String> text, @RequestParam Lang lang) {
        return subService.translateAll(text, lang);
    }

    @PostMapping("/dictionary")
    public List<WordDto> translateWithDictionaryAll(@RequestBody List<String> text, @RequestParam Lang lang) {
        return subService.translateWithDictionaryAll(text, lang);
    }

    @GetMapping
    public WordDto translate(@RequestParam String word, @RequestParam Lang lang) {
        return subService.translateWord(word, lang);
    }

}
