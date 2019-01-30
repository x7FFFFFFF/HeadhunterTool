package ru.alex.vic.hhtool.html.entities;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(value = RetentionPolicy.RUNTIME)
public @interface HtmlAttribute {


    String attrName();

    String attrValue();

    String valFromAttr() default "";

    Converter converter() default Converter.NONE;

    Class<?> collectionElementtype() default void.class;


}
