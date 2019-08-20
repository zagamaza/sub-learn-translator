package ru.zagamaza.translator.controller;

import lombok.RequiredArgsConstructor;
import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.zagamaza.translator.dto.CollectionDto;
import ru.zagamaza.translator.dto.WordDto;
import ru.zagamaza.translator.service.SubService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class Controller {

    private final SubService subService;

    @PostMapping("/result")
    public CollectionDto translate(@RequestParam("file") MultipartFile file) throws IOException {
        String string = IOUtils.toString(file.getInputStream());
        List<WordDto> words = subService.translateAll(string);
        return new CollectionDto(words, LocalDateTime.now(), "en-ru");
    }

        @GetMapping("/result")
    public WordDto translate(@RequestParam String word) {
        return subService.translateWord(word);
    }

}
