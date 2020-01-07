package ru.zagamaza.translator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import ru.zagamaza.translator.client.YandexTextTranslatorApi;
import ru.zagamaza.translator.client.YandexWordTranslatorDictApi;
import ru.zagamaza.translator.dto.Lang;
import ru.zagamaza.translator.dto.TranslationDto;
import ru.zagamaza.translator.dto.TranslationResultDto;
import ru.zagamaza.translator.dto.WordDto;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.util.CollectionUtils.isEmpty;

@Service
@RequiredArgsConstructor
public class TranslatorImpl implements Translator {

    private static final String TEXT_KEY = "text";

    private final YandexWordTranslatorDictApi yandexWordTranslatorDictApi;
    private final YandexTextTranslatorApi yandexTextTranslatorApi;

    @Value("${yandex.dict.key:}")
    private String[] yandexDictApiKey;

    @Value("${yandex.translate.key:}")
    private String[] yandexTranslateApiKey;

    @Override
    @Retryable(
            value = {Exception.class},
            maxAttempts = 5,
            backoff = @Backoff(delay = 1000, multiplier = 2))
    public WordDto translate(String source, Lang lang) {
        WordDto wordDto = new WordDto();
        wordDto.setLang(lang.toString());
        int randomNumber = (int)(Math.random() * (yandexDictApiKey.length));
        final String key = yandexDictApiKey[randomNumber];
        yandexWordTranslatorDictApi
                .translate(key, source, lang.toString())
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

    @Override
    @Retryable(
            value = {Exception.class},
            maxAttempts = 5,
            backoff = @Backoff(delay = 1000, multiplier = 2))
    public List<WordDto> translate(List<String> texts, Lang lang) {
        List<WordDto> words = new LinkedList<>();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>(4);

        for (String text : texts) {
            params.add(TEXT_KEY, text);
        }
        int randomNumber = (int)(Math.random() * (yandexTranslateApiKey.length));
        final String key = yandexTranslateApiKey[randomNumber];
        TranslationResultDto translate = yandexTextTranslatorApi
                .translate(key, lang.toString(), params);

        List<String> translateWords = translate.getTexts();
        for (int i = 0; i < texts.size(); i++) {
            words.add(WordDto.builder()
                             .lang(lang.toString())
                             .word(texts.get(i))
                             .mainTranslation(translateWords.get(i))
                             .build());
        }
        return words;
    }

}
