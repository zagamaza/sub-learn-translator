package ru.zagamaza.translator.service;

import ru.zagamaza.translator.dto.WordDto;

import java.util.List;

public interface SubService {

    WordDto translateWord(String text);

    List<WordDto> translateAll(String text);

}
