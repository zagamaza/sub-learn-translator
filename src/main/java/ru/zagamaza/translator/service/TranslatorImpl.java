package ru.zagamaza.translator.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import ru.zagamaza.translator.client.YandexWordTranslatorDictApi;
import ru.zagamaza.translator.client.YandexTextTranslatorApi;
import ru.zagamaza.translator.dto.ResponseDto;
import ru.zagamaza.translator.dto.TranslationResultDto;
import ru.zagamaza.translator.dto.WordDto;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TranslatorImpl implements Translator {

    private final YandexWordTranslatorDictApi yandexWordTranslatorDictApi;
    private final YandexTextTranslatorApi yandexTextTranslatorApi;

    @Value("${yandex.dict.key}")
    String yandexDictApiKey;

    @Value("${yandex.translate.key}")
    String yandexTranslateApiKey;

    @Override
    public WordDto translate(String source, String sourceLang, String targetLang) {
        String lang = sourceLang + "-" + targetLang;

        ResponseDto.Defenition defenition = yandexWordTranslatorDictApi
                .translate(yandexDictApiKey, source, lang)
                .getDef()
                .stream()
                .findFirst()
                .orElse(null);

        if (defenition == null) {
            return null;
        }
        return new WordDto(
                source,
                defenition.getTs(),
                defenition.getTr().stream()
                          .map(ResponseDto.Translate::getText)
                          .collect(Collectors.toList()),
                lang
        );
    }

    public List<WordDto> translate(List<String> texts, String sourceLang, String targetLang) {
        List<WordDto> words = new LinkedList<>();
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>(4);
        String lang = sourceLang + "-" + targetLang;

        for (String text : texts) {
            params.add("text", text);
        }
        TranslationResultDto translate = yandexTextTranslatorApi
                .translate(yandexTranslateApiKey, targetLang, params);

        List<String> translateWords = translate.getTexts();
        for (int i = 0; i < texts.size(); i++) {
            words.add(WordDto.builder()
                             .lang(lang)
                             .word(texts.get(i))
                             .translation(List.of(translateWords.get(i)))
                             .build());
        }
        return words;
    }

}
