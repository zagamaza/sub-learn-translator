package ru.zagamaza.translator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zagamaza.translator.dto.WordDto;
import ru.zagamaza.translator.util.SubUtil;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubServiceImpl implements SubService {

    private final Translator translator;

    @Override
    public WordDto translateWord(String text) {
        return translator.translate(text, "en", "ru");
    }

    @Override
    public List<WordDto> translateAll(String text) {
        List<String> words = SubUtil.getWords(text);
        return translator.translate(words, "en", "ru");
    }

}
