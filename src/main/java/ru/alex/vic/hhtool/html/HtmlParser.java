package ru.alex.vic.hhtool.html;

import org.joda.time.LocalDate;
import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeVisitor;
import ru.alex.vic.hhtool.html.entities.*;

import java.util.Map;

public class HtmlParser {


    public static <T> T parse(String html, Class<T> clss) throws Exception {
       /* final T instance = clss.newInstance();
        final Map<String, FieldWrapper> annotatedFields = Util.getAnnotatedFieldsMap(instance);
        NVisitor visitor = new NVisitor(annotatedFields);*/
        final Document document = Jsoup.parse(html);
        final Element body = document.body();
       /* body.traverse(visitor);
        return instance;*/
        return parse(body, clss);
    }

    public static <T> T parse(Node node, Class<T> clss) {
        final T instance;
        try {
            instance = clss.newInstance();
            final Map<String, FieldWrapper> annotatedFields = Util.getAnnotatedFieldsMap(instance);
            NVisitor visitor = new NVisitor(annotatedFields);
            node.traverse(visitor);
            return instance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }


    private static class NVisitor implements NodeVisitor {
        private final Map<String, FieldWrapper> annotatedFields;

        private NVisitor(Map<String, FieldWrapper> annotatedFields) {
            this.annotatedFields = annotatedFields;
        }

        @Override
        public void head(Node node, int i) {
            final Attributes attributes = node.attributes();
            for (Attribute attribute : attributes) {
                String key = Util.getKey(attribute);
                final FieldWrapper fieldWrapper = annotatedFields.get(key);
                if (fieldWrapper != null) {
                    final HtmlAttribute annotation = fieldWrapper.getAnnotation();
                    final Converter converter = annotation.converter();
                    if (converter.isNodeConverter()) {
                        fieldWrapper.set(annotation.converter().convert(node));
                    } else if (!annotation.valFromAttr().equals("")) {
                        String resultStr = node.attr(annotation.valFromAttr());
                        set(fieldWrapper, annotation, resultStr.trim());
                    } else if (node.childNodeSize() == 1) {
                        final Node child = node.childNode(0);
                        if (child instanceof TextNode) {
                            String resultStr = ((TextNode) child).text();
                            set(fieldWrapper, annotation, resultStr.trim());
                        }
                    }
                }
            }
        }

        private void set(FieldWrapper fieldWrapper, HtmlAttribute annotation, String resultStr) {
            if (annotation.converter() == Converter.NONE) {
                final Class<?> type = fieldWrapper.getField().getType();
                if (type == int.class) {
                    fieldWrapper.set(Converter.INT.convert(resultStr));
                } else if (LocalDate.class.isAssignableFrom(type)) {
                    fieldWrapper.set(Converter.DATE.convert(resultStr));
                } else {
                    fieldWrapper.set(resultStr);
                }
            } else {
                fieldWrapper.set(annotation.converter().convert(resultStr));
            }
        }

        @Override
        public void tail(Node node, int i) {

        }
    }


}
