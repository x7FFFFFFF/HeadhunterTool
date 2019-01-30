package ru.alex.vic.hhtool.html.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Sex {

    MALE("Мужчина"), FEMALE("Женщина");


    private final List<String> stringList = new ArrayList<>();

    Sex(String... str) {
        this.stringList.addAll(Arrays.asList(str));
    }

    public boolean match(String str) {
        return stringList.stream().anyMatch(s -> s.equalsIgnoreCase(str));
    }

    public static Sex convert(String str) {
        return Arrays.stream(Sex.values())
                .filter(s -> s.match(str))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
