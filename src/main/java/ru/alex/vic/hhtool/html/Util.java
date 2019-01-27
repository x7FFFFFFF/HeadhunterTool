package ru.alex.vic.hhtool.html;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeFilter;
import org.jsoup.select.NodeVisitor;
import ru.alex.vic.hhtool.html.entities.HtmlAttribute;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class Util {


    public static Map<String, Field> getFields(Class<?> clz) {
        return Arrays.stream(clz.getDeclaredFields()).collect(Collectors.toMap(Field::getName, f -> f
        ));
    }


    public static String getTextByAttr(Elements elements, Field field) {
        final HtmlAttribute annotation = field.getAnnotation(HtmlAttribute.class);
        Objects.requireNonNull(annotation);
        return getTextByAttr(elements, annotation);

    }


    public static String getTextByAttr(Elements elements, HtmlAttribute annotation) {
        Visitor visitor = new Visitor(annotation);
        elements.filter(visitor);
        return visitor.result;
    }

    public static boolean isDigit(String str) {
        return str.chars().allMatch(x -> Character.isDigit(x));
    }

    private static class Visitor implements NodeFilter {
        final String attributeName, attributeValue, valFromAttr;
        String result = "";


        private Visitor(HtmlAttribute annotation) {
            this.attributeName = annotation.attrName();
            this.attributeValue = annotation.attrValue();
            this.valFromAttr = annotation.valFromAttr();
        }


        @Override
        public FilterResult head(Node node, int depth) {
            if (node.hasAttr(attributeName) && node.attr(attributeName).equals(attributeValue)) {
                if (!valFromAttr.equals("")) {
                    result = node.attr(valFromAttr);
                    return FilterResult.STOP;
                } else if (node.childNodeSize() == 1) {
                    final Node child = node.childNode(0);
                    if (child instanceof TextNode) {
                        result = ((TextNode) child).text();
                        return FilterResult.STOP;
                    }
                }
            }
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }
}
