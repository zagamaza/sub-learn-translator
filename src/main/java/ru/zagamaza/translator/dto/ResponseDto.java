package ru.zagamaza.translator.dto;

import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ResponseDto {

    List<Defenition> def;

    @Data
    public static class Defenition {

        String text;
        String ts;
        String pos;
        List<Translate> tr;

        public List<String> toList(List<Translate> tr) {
            return tr.stream().map(Translate::getText).collect(Collectors.toList());
        }
    }

    @Data
    public static class Translate {
        String text;


    }

}
