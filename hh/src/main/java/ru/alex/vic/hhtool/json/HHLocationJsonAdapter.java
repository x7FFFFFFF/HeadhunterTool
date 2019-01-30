package ru.alex.vic.hhtool.json;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class HHLocationJsonAdapter extends TypeAdapter<HHLocationJson> {
    @Override
    public void write(JsonWriter out, HHLocationJson value) throws IOException {

    }

    @Override
    public HHLocationJson read(JsonReader in) throws IOException {
        return null;
    }
}
