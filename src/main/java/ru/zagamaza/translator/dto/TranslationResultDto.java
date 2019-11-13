package ru.zagamaza.translator.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TranslationResultDto {

    private String lang;
    @JsonProperty("text")
    private List<String> texts;

}