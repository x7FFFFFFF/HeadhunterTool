package ru.alex.vic.hhtool.html.entities;

import org.jsoup.nodes.Node;

public interface IConverter {

    Object convert(String str);

    default Object convert(Node node) {
        throw new UnsupportedOperationException();
    }

    default boolean isNodeConverter() {
        return false;
    }

}
