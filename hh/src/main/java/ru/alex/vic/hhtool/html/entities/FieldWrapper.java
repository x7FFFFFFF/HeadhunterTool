package ru.alex.vic.hhtool.html.entities;

import java.lang.reflect.Field;

public class FieldWrapper {
    private final Field field;
    private final HtmlAttribute annotation;
    private final Object instance;


    public FieldWrapper(Field field, HtmlAttribute annotation, Object instance) {
        this.field = field;
        this.annotation = annotation;
        this.instance = instance;
    }

    public Field getField() {
        return field;
    }

    public HtmlAttribute getAnnotation() {
        return annotation;
    }


    public void set(Object value) {
        final boolean accessible = field.isAccessible();
        field.setAccessible(true);
        try {
            field.set(instance, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        field.setAccessible(accessible);
    }
}
