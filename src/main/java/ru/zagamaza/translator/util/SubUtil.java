package ru.zagamaza.translator.util;

import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@UtilityClass
public class SubUtil {
    public List<String> getWords(String string) {
        Set<String> words = new TreeSet<>();
        String patternString = "\\b([a-zA-Z]{3,})\\b";
        Pattern pattern = Pattern.compile(patternString);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            words.add(matcher.group(1).toLowerCase());
        }
        return new ArrayList<>(words);
    }
}
