package ru.alex.vic.hhtool.html.entities;

import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import ru.alex.vic.hhtool.html.HtmlParser;
import ru.alex.vic.hhtool.html.Util;
import ru.alex.vic.hhtool.html.entities.IConverter;
import ru.alex.vic.hhtool.html.entities.Sex;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum Converter implements IConverter {

    NONE {
        @Override
        public Object convert(String str) {
            throw new UnsupportedOperationException();
        }
    },

    DATE {
        @Override
        public Object convert(String str) {
            return dateStringFormat.parseLocalDate(str);
        }
    },
    INT {
        @Override
        public Object convert(String str) {
            return Arrays.stream(str.split(DELIM))
                    .filter(Util::isDigit)
                    .map(Integer::valueOf)
                    .findFirst()
                    .orElseThrow(IllegalArgumentException::new);
        }

    },
    GENDER {
        @Override
        public Object convert(String str) {
            return Sex.convert(str);
        }
    },
    EDUCATION {
        @Override
        public Object convert(String str) {
            throw new UnsupportedOperationException();
        }

        @Override
        public Object convert(Node node) {
            List<Education> educations = new ArrayList<>();
            final List<Node> edNodes = Util.findNodes(node, "class", "resume-block-item-gap", false);
            for (Node edNode : edNodes) {
                final Education education = HtmlParser.parse(edNode, Education.class);
                //class="bloko-link bloko-link_list"
                Util.findFirstNode(node, "class", "bloko-link bloko-link_list")
                        .ifPresent(n -> {
                            if (n.childNodeSize() == 1) {
                                final String text = ((TextNode) n.childNode(0)).text();
                                final String[] split = text.split(",");
                                if (split.length == 2) {
                                    education.setCaption(split[0].trim());
                                    education.setCity(split[1].trim());
                                }
                            }
                        });
                //data-qa="resume-block-education-organization"
                Util.findFirstNode(node, "data-qa", "resume-block-education-organization").ifPresent(
                        n -> {
                            if (n.childNodeSize() == 1) {
                                final String text = ((TextNode) n.childNode(0)).text();
                                final String[] split = text.split(",");
                                if (split.length == 2) {
                                    education.setDepartment(split[0].trim());
                                    education.setSpecialization(split[1].trim());
                                }
                            }
                        }
                );

                educations.add(education);
            }

            return educations;
        }

        @Override
        public boolean isNodeConverter() {
            return true;
        }
    };


    private static final DateTimeFormatter dateStringFormat = DateTimeFormat.forPattern("yyyy-MM-dd");

    public static final String DELIM = " ";

    public static Sex convertGender(String gender) {
        return Sex.convert(gender);
    }

    public static int convertAge(String text) {
        return Arrays.stream(text.split(DELIM))
                .filter(Util::isDigit)
                .map(Integer::valueOf)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

    }


    public static BigDecimal convertSalary(String text) {
        return null;
    }

    public static LocalDate convertDate(String text) {
        return dateStringFormat.parseLocalDate(text);
    }
}
