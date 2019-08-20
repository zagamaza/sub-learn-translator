package ru.zagamaza.translator.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zagamaza.translator.dto.TranslationResultDto;

@FeignClient(contextId = "wordTranslator", value = "wordTranslator", url = "${yandex.translate.url}")
public interface YandexTextTranslatorApi {

    @PostMapping("/translate")
    TranslationResultDto translate(
            @RequestParam("key") String key,
            @RequestParam("lang") String lang,
            @RequestBody MultiValueMap<String, String> params
    );

}
