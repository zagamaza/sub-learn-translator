package ru.zagamaza.translator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.zagamaza.translator.client.YandexTextTranslatorApi;
import ru.zagamaza.translator.client.YandexWordTranslatorDictApi;
import ru.zagamaza.translator.dto.Lang;
import ru.zagamaza.translator.dto.TranslationDto;
import ru.zagamaza.translator.dto.TranslationResultDto;
import ru.zagamaza.translator.dto.WordDto;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TranslatorImpl implements Translator {

    private static final String TEXT_KEY = "text";

    private final YandexWordTranslatorDictApi yandexWordTranslatorDictApi;
    private final YandexTextTranslatorApi yandexTextTranslatorApi;

    @Value("${yandex.dict.key}")
    String yandexDictApiKey;

    @Value("${yandex.translate.key}")
    String yandexTranslateApiKey;

    @Override
    public WordDto translate(String source, Lang lang) {
        WordDto wordDto = new WordDto();
        wordDto.setLang(lang.toString());

        yandexWordTranslatorDictApi
                .translate(yandexDictApiKey, source, lang.toString())
                .getDef()
                .forEach(defenition -> {
                    wordDto.setTranscription(defenition.getTs());
                    wordDto.setWord(defenition.getText());
                    wordDto.getTranslation()
                           .add(new TranslationDto(
                                   defenition.getPos(),
                                   defenition.toList(defenition.getTr())
                           ));
                });

        return wordDto;
    }

    public List<WordDto> translate(List<String> texts, Lang lang) {
        List<WordDto> words = new LinkedList<>();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>(4);

        for (String text : texts) {
            params.add(TEXT_KEY, text);
        }
        TranslationResultDto translate = yandexTextTranslatorApi
                .translate(yandexTranslateApiKey, lang.toString(), params);

        List<String> translateWords = translate.getTexts();
        for (int i = 0; i < texts.size(); i++) {
            words.add(WordDto.builder()
                             .lang(lang.toString())
                             .word(texts.get(i))
                             .translation(List.of(new TranslationDto(null, List.of(translateWords.get(i)))))
                             .build());
        }
        return words;
    }

    @Override
    public List<WordDto> translateWithDictionary(List<String> source, Lang lang) {
        return source.parallelStream()
                     .map(word -> translate(word, lang))
                     .collect(Collectors.toList());
    }

}
