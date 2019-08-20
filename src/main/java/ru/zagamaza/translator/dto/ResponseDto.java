package ru.zagamaza.translator.dto;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDto {
    List<Defenition> def;

    @Data
    public static class Defenition {
        String text;
        String ts;
        List<Translate> tr;
    }

    @Data
    public static class Translate {
        String text;
    }
}
