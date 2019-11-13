package ru.zagamaza.translator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.zagamaza.translator.dto.Lang;
import ru.zagamaza.translator.dto.WordDto;

import java.util.Collections;
import java.util.List;

import static org.springframework.util.CollectionUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class SubServiceImpl implements SubService {

    private final Translator translator;

    @Override
    public WordDto translateWord(String text, Lang lang) {
        return translator.translate(text, lang);
    }

    @Override
    public List<WordDto> translateAll(List<String> words, Lang lang) {
        if (isEmpty(words)) {
            return Collections.emptyList();
        }
        return translator.translate(words, lang);
    }

    @Override
    public List<WordDto> translateWithDictionaryAll(List<String> words, Lang lang) {
        if (isEmpty(words)) {
            return Collections.emptyList();
        }
        return translator.translateWithDictionary(words, lang);
    }

}
