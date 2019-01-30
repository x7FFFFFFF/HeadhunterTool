package ru.alex.vic.hhtool.html;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Node;
import org.jsoup.select.NodeFilter;
import ru.alex.vic.hhtool.html.entities.FieldWrapper;
import ru.alex.vic.hhtool.html.entities.HtmlAttribute;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

public class Util {


    public static <T> Map<String, FieldWrapper> getAnnotatedFieldsMap(T instance) throws IllegalAccessException, InstantiationException {
        final Field[] declaredFields = instance.getClass().getDeclaredFields();
        Map<String, FieldWrapper> res = new HashMap<>();
        for (Field declaredField : declaredFields) {
            final HtmlAttribute annotation = declaredField.getAnnotation(HtmlAttribute.class);
            if (annotation != null) {
                res.put(getKey(annotation), new FieldWrapper(declaredField, annotation, instance));
            }
        }
        return res;
    }

    public static String getKey(HtmlAttribute annotation) {
        return annotation.attrName() + "=" + annotation.attrValue();
    }

    public static String getKey(Attribute attribute) {
        return attribute.getKey() + "=" + attribute.getValue();
    }


    public static Map<String, Field> getAnnotatedFields(Class<?> clz) {
        return Arrays.stream(clz.getDeclaredFields())
                .filter(f -> f.getAnnotation(HtmlAttribute.class) != null)
                .collect(Collectors.toMap(Field::getName, f -> f
                ));
    }


  /*  public static String getTextByAttr(Elements elements, Field field) {
        final HtmlAttribute annotation = field.getAnnotation(HtmlAttribute.class);
        Objects.requireNonNull(annotation);
        return getTextByAttr(elements, annotation);

    }*/

    public static Optional<Node> findFirstNode(Node elements, String attributeName, String attributeValue) {
        final List<Node> nodes = findNodes(elements, attributeName, attributeValue, true);
        return (nodes.size() == 0) ? Optional.empty() : Optional.of(nodes.get(0));
    }

    public static List<Node> findNodes(Node elements, String attributeName, String attributeValue, boolean onlyFirst) {
        NFilter visitor = new NFilter(attributeName, attributeValue, onlyFirst);
        elements.filter(visitor);
        return visitor.result;
    }

    public static boolean isDigit(String str) {
        return str.chars().allMatch(Character::isDigit);
    }

    private static class NFilter implements NodeFilter {
        final String attributeName;
        final String attributeValue;
        private final boolean onlyFirst;
        List<Node> result = new ArrayList<>();


        public NFilter(String attributeName, String attributeValue, boolean onlyFirst) {
            this.attributeName = attributeName;
            this.attributeValue = attributeValue;

            this.onlyFirst = onlyFirst;
        }

        @Override
        public FilterResult head(Node node, int depth) {
            if (node.hasAttr(attributeName) && node.attr(attributeName).equals(attributeValue)) {
                result.add(node);
                if (onlyFirst) {
                    return FilterResult.STOP;
                }
            }
            return FilterResult.CONTINUE;
        }

        @Override
        public FilterResult tail(Node node, int depth) {
            return FilterResult.CONTINUE;
        }
    }

    public static boolean isCollection(HtmlAttribute annotation) {
        return annotation.collectionElementtype() != void.class;
    }

}
