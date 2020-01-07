package ru.zagamaza.translator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.zagamaza.translator.dto.Lang;
import ru.zagamaza.translator.dto.WordDto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
        List<WordDto> translate = translator.translate(words, lang);
        return translate.parallelStream()
                        .peek(w -> {
                            WordDto wordDto = translator.translate(w.getWord(), lang);
                            w.setTranscription(wordDto.getTranscription());
                            w.setTranslation(wordDto.getTranslation());
                        })
                        .filter(w -> !StringUtils.isEmpty(w.getTranscription()) || !isEmpty(w.getTranslation()))
                        .collect(Collectors.toList());

    }

}
