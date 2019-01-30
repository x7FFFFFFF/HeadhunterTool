package ru.alex.vic.hhtool.jpa;

import ru.alex.vic.hhtool.json.HHLocationJson;

import java.util.function.Consumer;

public class HHLocatinsUtil {


    public static void iterate(HHLocationJson[] hhLocationJsons, Consumer<HHLocation> consumer) {
        for (HHLocationJson hhLocationJson : hhLocationJsons) {
            consumer.accept(new HHLocation(hhLocationJson));
            iterate( hhLocationJson.getAreas(), consumer);
        }
    }

}
