package ru.zagamaza.translator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WordDto {

    private String word;
    private String transcription;
    private String mainTranslation;
    private List<TranslationDto> translation;
    private String lang;


    public List<TranslationDto> getTranslation() {
        if (translation == null) {
            translation = new ArrayList<>();
        } return translation;
    }


}
