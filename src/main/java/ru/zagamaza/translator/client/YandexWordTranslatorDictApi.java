package ru.zagamaza.translator.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.zagamaza.translator.dto.ResponseDto;

@FeignClient(contextId = "textTranslator", value = "textTranslator", url = "${yandex.dict.url}")
public interface YandexWordTranslatorDictApi {

    @GetMapping("/lookup")
    ResponseDto translate(
            @RequestParam("key") String key,
            @RequestParam("text") String text,
            @RequestParam("lang") String lang
    );

}
