package ru.zagamaza.translator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class TranslationDto {

    private String partSpeech;
    private List<String> translate;

}
