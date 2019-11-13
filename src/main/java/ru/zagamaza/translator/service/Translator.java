package ru.zagamaza.translator.service;

import ru.zagamaza.translator.dto.Lang;
import ru.zagamaza.translator.dto.WordDto;

import java.util.List;

public interface Translator {

    WordDto translate(String source, Lang lang);

    List<WordDto> translate(List<String> source, Lang lang);

    List<WordDto> translateWithDictionary(List<String> source, Lang lang);

}
