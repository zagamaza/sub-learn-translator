package ru.zagamaza.translator.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WordDto {
    private String word;
    private String transcription;
    private List<String> translation;
    private String lang;

}
