package ru.zagamaza.translator.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CollectionDto {

    private List<WordDto> words;
    private LocalDateTime created;
    private String lang;


}
