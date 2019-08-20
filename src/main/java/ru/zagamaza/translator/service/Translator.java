package ru.zagamaza.translator.service;

import ru.zagamaza.translator.dto.WordDto;

import java.util.List;

public interface Translator {

    WordDto translate(String source, String sourceLang, String targetLang);

    List<WordDto> translate(List<String> source, String sourceLang, String targetLang);

}
