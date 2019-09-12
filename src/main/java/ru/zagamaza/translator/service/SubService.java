package ru.zagamaza.translator.service;

import ru.zagamaza.translator.dto.Lang;
import ru.zagamaza.translator.dto.WordDto;

import java.util.List;

public interface SubService {

    WordDto translateWord(String text, Lang lang);

    List<WordDto> translateAll(List<String> words, Lang lang);

    List<WordDto> translateWithDictionaryAll(List<String> text, Lang lang);

}
